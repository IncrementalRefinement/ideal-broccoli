package com.example.idealbroccoli.service;

import com.example.idealbroccoli.util.job.RunnableWithId;

public interface SchedulerService {

    public void registerJob(RunnableWithId job, long rate);

    public void cancelJob(long taskId);

    public void executeJobOnce(RunnableWithId job);
}
