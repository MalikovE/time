package mocks;

import time.job.Job;

public class JobMock implements Job {
    @Override
    public void execute() {
        System.out.println("I do job.");
    }
}
