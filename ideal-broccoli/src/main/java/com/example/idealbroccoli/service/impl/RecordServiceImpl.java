package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.entity.Record;

import com.example.idealbroccoli.service.RecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Override
    public List<Record> queryRecordByJobId(Long JobId) {
        return null;
    }
}
