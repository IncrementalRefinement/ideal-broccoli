package com.example.idealbroccoli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadPoolTaskSchedulerConfig {

    private static final int DEFAULT_POOL_SIZE = 5;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler ret = new ThreadPoolTaskScheduler();
        ret.setPoolSize(5);
        return ret;
    }
}
