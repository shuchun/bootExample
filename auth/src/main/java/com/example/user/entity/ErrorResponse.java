package com.example.user.entity;

/**
 * Created by IBM on 2016/8/1.
 * 错误响应类
 */
public class ErrorResponse {
    private String error;

    public ErrorResponse() {
    }

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
