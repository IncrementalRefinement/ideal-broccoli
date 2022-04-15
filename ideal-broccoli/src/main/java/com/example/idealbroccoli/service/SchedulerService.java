package com.example.idealbroccoli.service;

import com.example.idealbroccoli.util.job.RunnableWithId;

public interface SchedulerService {

    public void registerTask(RunnableWithId task, long rate);

    public void cancelTask(long taskId);
}
