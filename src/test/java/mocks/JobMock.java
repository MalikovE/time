package mocks;

import time.scheduler.Job;

public class JobMock implements Job {
    @Override
    public void execute() {
        System.out.println("I do job.");
    }
}
