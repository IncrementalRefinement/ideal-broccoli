package com.example.idealbroccoli.controller;


import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {

    @Autowired
    JobService jobService;

    @PostMapping
    public ResponseEntity createJob(@Valid @RequestBody Job job) {
        // TODO
        jobService.createNewJob(job);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Job>> queryJob() {
        List<Job> jobList = jobService.queryAllJob();
        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Job> deleteJob(@RequestParam(name = "jobId") Long id) {
        Job deletedJob = jobService.deleteByID(id);
        return new ResponseEntity<>(deletedJob, HttpStatus.OK);
    }

}
