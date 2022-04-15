package com.example.idealbroccoli.service;

import com.example.idealbroccoli.entity.Job;

public interface FlinkService {

    public void addJob(Job theJob);

    public void deleteJob(Job theJob);
}
