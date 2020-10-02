import mocks.ClockMock;
import mocks.NtpClientMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import time.clock.Clock;
import time.job.Job;
import time.job.TimeSyncJob;
import time.ntp.NtpClient;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;

public class TimeSyncJobTest {

    private Job job;
    private Clock clock;

    @Before
    public void setUp() throws Exception {
        InetSocketAddress ntpServerAddress = new InetSocketAddress(InetAddress.getLocalHost(), 80);
        NtpClient ntpClient = new NtpClientMock(ntpServerAddress);
        clock = new ClockMock();
        job = new TimeSyncJob(ntpClient, clock);
    }

    @Test
    public void testTimeSyncJob() {
        LocalDateTime expectedDateTime = LocalDateTime.now();

        job.execute();

        Assert.assertEquals(expectedDateTime, clock.getDateTime());
    }
}
