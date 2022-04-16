package com.example.idealbroccoli.controller;


import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.payload.UniversalResponse;
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
    public ResponseEntity<UniversalResponse<Void>> createJob(@Valid @RequestBody Job job) {
        jobService.createNewJob(job);

        UniversalResponse<Void> response = new UniversalResponse<>();
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UniversalResponse<List<Job>>> queryJob() {
        List<Job> jobList = jobService.queryAllJob();

        UniversalResponse<List<Job>> response = new UniversalResponse<>();
        response.setSuccess(true);
        response.setPayload(jobList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<UniversalResponse<Job>> deleteJob(@RequestParam(name = "jobId") Long id) {
        Job deletedJob = jobService.deleteByID(id);

        UniversalResponse<Job> response = new UniversalResponse<>();
        response.setSuccess(true);
        response.setPayload(deletedJob);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
