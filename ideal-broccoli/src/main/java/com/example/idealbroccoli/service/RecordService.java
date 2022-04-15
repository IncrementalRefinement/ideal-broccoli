package com.example.idealbroccoli.service;

import com.example.idealbroccoli.entity.Record;

import java.util.List;

public interface RecordService {

    List<Record> queryRecordByJobId(Long JobId);
}
