package com.example.idealbroccoli.util.job;

public class TestRunnable extends RunnableWithId {

    private final String message;

    public TestRunnable(long id, String message) {
        super(id);
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(message);
    }
}
