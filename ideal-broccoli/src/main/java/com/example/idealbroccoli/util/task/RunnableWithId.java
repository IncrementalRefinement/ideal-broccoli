package com.example.idealbroccoli.util.task;

public abstract class RunnableWithId implements Runnable {

    private final Long id;

    public RunnableWithId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
