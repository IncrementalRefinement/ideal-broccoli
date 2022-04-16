package com.example.idealbroccoli.repository;

import com.example.idealbroccoli.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    public List<Record> findAllByJobId(Long jobId);
}
