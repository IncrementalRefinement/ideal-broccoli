package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.entity.Record;

import com.example.idealbroccoli.repository.RecordRepository;
import com.example.idealbroccoli.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Override
    public List<Record> queryRecordByJobId(Long jobId) {
        return recordRepository.findAllByJobId(jobId);
    }

    @Override
    public void createNewRecord(Record newRecord) {
        recordRepository.save(newRecord);
    }
}
