package com.example.idealbroccoli.service;

import org.springframework.stereotype.Service;

@Service
public interface KafkaService {

    public void sendMessage(String message);

    public void consumeMessage(String message);
}
