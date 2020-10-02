package mocks;

import time.job.Job;

public class JobMock implements Job {

    private int jobCount;

    public JobMock() {
        jobCount = 0;
    }

    public int getJobCount() {
        return jobCount;
    }

    @Override
    public void execute() {
        jobCount++;
    }
}
