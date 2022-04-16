package com.example.idealbroccoli.util.serilization;

import com.example.idealbroccoli.entity.Job;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class JobJsonSerialization {

    public static Job deserialize(String theJsonJob) {
        Gson gson = new Gson();
        return gson.fromJson(theJsonJob, Job.class);
    }

    public static String serialize(Job theJob) {
        Gson gson = new Gson();
        return gson.toJson(theJob, Job.class);
    }
}
