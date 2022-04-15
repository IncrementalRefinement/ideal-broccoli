package com.example.idealbroccoli.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UniversalResponse<T> {

    private boolean success;
    private T payload;
    private UniversalError error;

    public UniversalResponse() {};

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public UniversalError getError() {
        return error;
    }

    public void setError(UniversalError error) {
        this.error = error;
    }
}
