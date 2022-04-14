package com.example.idealbroccoli.service;

import com.example.idealbroccoli.util.task.RunnableWithId;

public interface SchedulerService {

    public void registerTask(RunnableWithId task, long rate);

    public void cancelTask(long taskId);
}
