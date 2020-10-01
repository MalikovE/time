package time.scheduler;

public class SchedulerImpl implements Scheduler {

    private int[] schedule;
    private Job job;

    public SchedulerImpl(Job job) {
        this.job = job;
    }

    @Override
    public int[] getSchedule() {
        return schedule;
    }

    @Override
    public void setSchedule(int[] schedule) {
        this.schedule = schedule;
    }

    @Override
    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public void doJob() {
        for (int hour : schedule) {
            job.execute();
        }
    }
}
