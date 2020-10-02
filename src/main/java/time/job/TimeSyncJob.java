package time.job;

import time.clock.Clock;
import time.ntp.NtpClient;

import java.time.LocalDateTime;

public class TimeSyncJob implements Job {

    private NtpClient ntpClient;
    private Clock clock;

    public TimeSyncJob(NtpClient ntpClient, Clock clock) {
        this.ntpClient = ntpClient;
        this.clock = clock;
    }

    @Override
    public void execute() {
        LocalDateTime ntpTime = ntpClient.getTime();
        clock.setDateTime(ntpTime);
    }
}
