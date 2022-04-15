package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.service.SchedulerService;
import com.example.idealbroccoli.util.job.RunnableWithId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final Map<Long, ScheduledFuture<?>> registeredSchedulers = new HashMap<>();

    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public void registerTask(RunnableWithId job, long rate) {
        ScheduledFuture<?> future = threadPoolTaskScheduler.scheduleAtFixedRate(job, rate);
        registeredSchedulers.put(job.getId(), future);
    }

    public void cancelTask(long taskId) {
        ScheduledFuture<?> future = registeredSchedulers.get(taskId);
        future.cancel(false);
        registeredSchedulers.remove(taskId);
    }
}
