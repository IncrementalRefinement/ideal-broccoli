package com.example.idealbroccoli.repository;

import com.example.idealbroccoli.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
