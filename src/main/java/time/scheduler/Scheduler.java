package time.scheduler;

public interface Scheduler {
    int[] getSchedule();
    void setSchedule(int[] schedule);
    void setJob(Job job);
    void doJob();
}
