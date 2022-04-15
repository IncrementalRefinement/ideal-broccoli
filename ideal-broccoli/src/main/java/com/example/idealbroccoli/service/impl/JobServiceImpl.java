package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.repository.JobRepository;
import com.example.idealbroccoli.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UniversalJobControlServiceImpl jobController;

    @Override
    @Transactional
    public Job deleteByID(Long id) {
        Job theJob = jobRepository.getById(id);
        jobRepository.delete(theJob);
        jobController.deleteJob(theJob);
        return theJob;
    }

    @Override
    public void createNewJob(Job job) {
        jobRepository.save(job);
        jobController.addJob(job);
    }

    @Override
    public List<Job> queryAllJob() {
        return jobRepository.findAll();
    }
}
