package time.ntp;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

import java.net.InetSocketAddress;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class NtpClientImpl implements NtpClient {

    private static final int NTP_SERVER_TIMEOUT_MS = 10_000;

    private NTPUDPClient client;
    private InetSocketAddress ntpServer;

    public NtpClientImpl(InetSocketAddress ntpServer) {
        if (ntpServer != null) {
            this.ntpServer = ntpServer;
        }
        client = new NTPUDPClient();
    }

    @Override
    public InetSocketAddress getNtpServer() {
        return this.ntpServer;
    }

    @Override
    public LocalDateTime getTime() {
        TimeStamp ntpTime = null;
        try {
            client.setDefaultTimeout(NTP_SERVER_TIMEOUT_MS);
            TimeInfo timeInfo = client.getTime(ntpServer.getAddress(), ntpServer.getPort());
            timeInfo.computeDetails();
            if (timeInfo.getOffset() != null) {
                long offset = timeInfo.getOffset();
                long currentTime = System.currentTimeMillis();
                ntpTime = TimeStamp.getNtpTime(currentTime + offset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ntpTime == null) return null;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(ntpTime.getTime()),
                        TimeZone.getDefault().toZoneId());
    }
}
