import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import time.ntp.NtpClient;
import mocks.NtpClientMock;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class NtpClientTest {

    private NtpClient ntpClient;

    @Before
    public void setUp() throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 80);
        ntpClient = new NtpClientMock(inetSocketAddress);
    }

    @Test
    public void testGetTime() throws UnknownHostException {
        LocalDateTime ntpTime = ntpClient.getTime();
        LocalDateTime expectedTime = LocalDateTime.now();

        ZoneOffset zoneOffset = ZoneOffset.UTC;
        Assert.assertEquals(expectedTime.toInstant(zoneOffset), ntpTime.toInstant(zoneOffset));
    }
}
