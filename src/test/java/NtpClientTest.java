import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import time.ntp.NtpClient;
import time.ntp.NtpClientMock;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class NtpClientTest {

    private NtpClient ntpClient;

    @Before
    public void setUp() throws Exception {
        ntpClient = new NtpClientMock(InetAddress.getLocalHost());
    }

    @Test
    public void testGetTime() throws UnknownHostException {
        LocalDateTime ntpTime = ntpClient.getTime();
        LocalDateTime expectedTime = LocalDateTime.now();

        ZoneOffset zoneOffset = ZoneOffset.UTC;
        Assert.assertEquals(expectedTime.toInstant(zoneOffset), ntpTime.toInstant(zoneOffset));
    }
}
