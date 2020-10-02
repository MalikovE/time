package mocks;

import org.apache.commons.net.ntp.NtpUtils;
import org.apache.commons.net.ntp.NtpV3Impl;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeStamp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class NtpServerMock implements Runnable {

    private int port;

    private volatile boolean running;
    private boolean started;

    private DatagramSocket socket;

    public NtpServerMock(int port) {
        if (port < 0) {
            throw new IllegalArgumentException();
        }
        this.port = port;
    }

    public int getPort()
    {
        return port;
    }

    public boolean isRunning()
    {
        return running;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void connect() throws IOException {
        if (socket == null)
        {
            socket = new DatagramSocket(port);
            // port = 0 is bound to available free port
            if (port == 0) {
                port = socket.getLocalPort();
            }
            System.out.println("Running NTP service on port " + port + "/UDP");
        }
    }

    public void start() throws IOException {
        if (socket == null)
        {
            connect();
        }
        if (!started)
        {
            started = true;
            new Thread(this).start();
        }
    }

    @Override
    public void run() {
        running = true;
        byte[] buffer = new byte[48];
        final DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        do {
            try {
                socket.receive(request);
                final long rcvTime = System.currentTimeMillis();
                handlePacket(request, rcvTime);
            } catch (IOException e) {
                if (running)
                {
                    e.printStackTrace();
                }
                // otherwise socket thrown exception during shutdown
            }
        } while (running);
    }

    protected void handlePacket(DatagramPacket request, long rcvTime) throws IOException {
        NtpV3Packet message = new NtpV3Impl();
        message.setDatagramPacket(request);
        System.out.printf("NTP packet from %s mode=%s%n", request.getAddress().getHostAddress(),
                NtpUtils.getModeName(message.getMode()));
        if (message.getMode() == NtpV3Packet.MODE_CLIENT) {
            NtpV3Packet response = new NtpV3Impl();

            response.setStratum(1);
            response.setMode(NtpV3Packet.MODE_SERVER);
            response.setVersion(NtpV3Packet.VERSION_3);
            response.setPrecision(-20);
            response.setPoll(0);
            response.setRootDelay(62);
            response.setRootDispersion((int) (16.51 * 65.536));

            // originate time as defined in RFC-1305 (t1)
            response.setOriginateTimeStamp(message.getTransmitTimeStamp());
            // Receive Time is time request received by server (t2)
            response.setReceiveTimeStamp(TimeStamp.getNtpTime(rcvTime));
            response.setReferenceTime(response.getReceiveTimeStamp());
            response.setReferenceId(0x4C434C00); // LCL (Undisciplined Local Clock)

            // Transmit time is time reply sent by server (t3)
            response.setTransmitTime(TimeStamp.getNtpTime(System.currentTimeMillis()));

            DatagramPacket dp = response.getDatagramPacket();
            dp.setPort(request.getPort());
            dp.setAddress(request.getAddress());
            socket.send(dp);
        }
        // otherwise if received packet is other than CLIENT mode then ignore it
    }

    public void stop() {
        running = false;
        if (socket != null)
        {
            socket.close();  // force closing of the socket
            socket = null;
        }
        started = false;
    }

    public static void main(String[] args) {
        int port = NtpV3Packet.NTP_PORT;
        if (args.length != 0)
        {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        NtpServerMock timeServer = new NtpServerMock(port);
        try {
            timeServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}