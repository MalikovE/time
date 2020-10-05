package time.job;

public class StopFlagCheckJob implements Job {
    @Override
    public void execute() {
        System.out.println("Stop flag checking");
    }
}
