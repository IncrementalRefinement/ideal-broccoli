package com.example.idealbroccoli.service;

import com.example.idealbroccoli.entity.Job;

import java.util.List;

public interface JobService {

    public Job deleteByID(Long id);

    public void createNewJob(Job job);

    public List<Job> queryAllJob();
}
