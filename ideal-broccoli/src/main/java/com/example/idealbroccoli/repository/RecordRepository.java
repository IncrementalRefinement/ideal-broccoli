package com.example.idealbroccoli.repository;

import com.example.idealbroccoli.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
