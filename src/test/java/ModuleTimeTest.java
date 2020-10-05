import time.job.Job;
import time.job.StopFlagCheckJob;
import time.job.SyncJob;
import time.job.UpdateActivityJob;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ModuleTimeTest {
    public static void main(String[] args) {

        Job updateActivityJob = new UpdateActivityJob();
        Job stopFlagCheckJob = new StopFlagCheckJob();
        Job syncJob = new SyncJob();

        syncJob.execute();

        ScheduledExecutorService baseScheduler = Executors.newSingleThreadScheduledExecutor();

        baseScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                updateActivityJob.execute();
            }
        }, 0, 10, TimeUnit.SECONDS);

        baseScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                stopFlagCheckJob.execute();
            }
        }, 0, 60, TimeUnit.SECONDS);

        List<LocalTime> triggers = new ArrayList<>();
        triggers.add(LocalTime.of(17, 33));
        triggers.add(LocalTime.of(17, 35));
        triggers.add(LocalTime.of(17, 38));
        triggers.add(LocalTime.of(17, 40));

        baseScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LocalTime now = LocalTime.now();
                for (LocalTime trigger : triggers) {
                    if (now.getHour() == trigger.getHour() && now.getMinute() == trigger.getMinute()) {
                        syncJob.execute();
                    }
                }
            }
        }, 0, 60, TimeUnit.SECONDS);
    }
}
