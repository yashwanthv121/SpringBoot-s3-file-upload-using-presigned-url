package com.app.yash.s3fileuploadusingpresignedurl;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private int status;
    private String message;
    private String keyValue;
    private T presignedUrl;

    public ApiResponse(int status, String message, String keyValue, T presignedUrl) {
        this.status = status;
        this.message = message;
        this.keyValue = keyValue;
        this.presignedUrl = presignedUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPresignedUrl() {
        return presignedUrl;
    }

    public void setPresignedUrl(T presignedUrl) {
        this.presignedUrl = presignedUrl;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
