package com.example.myproject.exception;

public class CustomerException extends RuntimeException {
    private String errorCode;

    public CustomerException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public CustomerException(String msg, String errorCode, Throwable t) {
        super(msg, t);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}