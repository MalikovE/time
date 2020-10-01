package time.scheduler;

import time.ntp.NtpClient;
import time.os.TimeSetter;

import java.time.LocalDateTime;

public class TimeSyncJob implements Job {

    private NtpClient ntpClient;
    private TimeSetter timeSetter;

    public TimeSyncJob(NtpClient ntpClient, TimeSetter timeSetter) {
        this.ntpClient = ntpClient;
        this.timeSetter = timeSetter;
    }

    @Override
    public void execute() {
        LocalDateTime ntpTime = ntpClient.getTime();
        timeSetter.setTime(ntpTime);
    }
}
