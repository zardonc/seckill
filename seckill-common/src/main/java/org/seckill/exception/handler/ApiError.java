package org.seckill.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.seckill.base.StatusCode;

import java.time.LocalDateTime;

public class ApiError {
    private Integer status = 400;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    public ApiError() {
        timestamp = LocalDateTime.now();
    }

    public static ApiError error(String message){
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    public static ApiError error(Integer status, String message){
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        return apiError;
    }

    public static ApiError error(StatusCode statusCode){
        ApiError apiError = new ApiError();
        apiError.setStatus(statusCode.getCode());
        apiError.setMessage(statusCode.getMsg());
        return apiError;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
