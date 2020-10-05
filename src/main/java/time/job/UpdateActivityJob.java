package time.job;

public class UpdateActivityJob implements Job {
    @Override
    public void execute() {
        System.out.println("Updating activity");
    }
}
