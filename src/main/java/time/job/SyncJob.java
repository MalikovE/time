package time.job;

public class SyncJob implements Job {
    @Override
    public void execute() {
        System.out.println("Sync time");
    }
}
