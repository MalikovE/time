package time.scheduler;

public class JobMock implements Job {
    @Override
    public void execute() {
        System.out.println("I do job.");
    }
}
