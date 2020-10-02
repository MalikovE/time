import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import time.ntp.NtpClient;
import mocks.NtpClientMock;
import time.os.OS;
import mocks.OSMock;
import time.os.TimeSetter;
import mocks.TimeSetterMock;
import time.scheduler.Job;
import time.scheduler.Scheduler;
import time.scheduler.SchedulerImpl;
import time.scheduler.TimeSyncJob;

import java.net.InetAddress;

public class SchedulerTest {

    private Scheduler scheduler;
    private OS os;

    @Before
    public void setUp() throws Exception {
        os = new OSMock();
        NtpClient ntpClient = new NtpClientMock(InetAddress.getLocalHost());
        TimeSetter timeSetter = new TimeSetterMock(os);
        Job job = new TimeSyncJob(ntpClient, timeSetter);
        scheduler = new SchedulerImpl(job);
    }

    @Test
    public void testJobSchedule() {
        int[] schedule = new int[] { 1, 2, 3 };
        scheduler.setSchedule(schedule);

        scheduler.doJob();

        int expectedResult = 3;
        Assert.assertEquals(expectedResult, os.getOperationCount());
    }
}
