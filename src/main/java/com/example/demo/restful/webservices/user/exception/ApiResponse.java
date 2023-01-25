package com.example.demo.restful.webservices.user.exception;

public class ApiResponse {

    private String message;
    private boolean success;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse() {
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}