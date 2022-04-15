package com.example.idealbroccoli.controller;

import com.example.idealbroccoli.entity.Record;
import com.example.idealbroccoli.payload.UniversalResponse;
import com.example.idealbroccoli.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/record")
public class RecordController {

    @Autowired
    RecordService recordService;

    @GetMapping
    public ResponseEntity<UniversalResponse<List<Record>>> queryRecord(@RequestParam(name = "jobId") Long jobId) {
        List<Record> recordList = recordService.queryRecordByJobId(jobId);
        UniversalResponse<List<Record>> response = new UniversalResponse<>();
        response.setSuccess(true);
        response.setPayload(recordList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
