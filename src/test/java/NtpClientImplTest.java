import mocks.NtpServerMock;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import time.ntp.NtpClient;
import time.ntp.NtpClientImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class NtpClientImplTest {

    private static final int ALLOWABLE_ERROR = 5;

    private static NtpServerMock server;
    private static NtpClient client;

    @BeforeClass
    public static void oneTimeSetUp() throws Exception {
        server = new NtpServerMock(0);
        server.connect();

        try {
            server.start();
        } catch (IOException e) {
            Assert.fail("failed to start NTP server: " + e);
        }
        Assert.assertTrue(server.isStarted());

        boolean running = false;
        for (int retries = 0; retries < 5; retries++) {
            running = server.isRunning();
            if (running) {
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}
        }
        Assert.assertTrue(running);
    }

    @AfterClass
    public static void oneTimeTearDown() {
        if (server != null) {
            server.stop();
            server = null;
        }
    }

    @Test
    public void testGetTime() {
        InetSocketAddress ntpServer = new InetSocketAddress("localhost", server.getPort());
        client = new NtpClientImpl(ntpServer);
        Assert.assertTrue(ChronoUnit.MILLIS.between(LocalDateTime.now(), client.getTime()) < ALLOWABLE_ERROR);
    }
}
