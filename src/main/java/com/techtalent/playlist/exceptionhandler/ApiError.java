package com.techtalent.playlist.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;


    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(String message, Throwable ex) {
        this();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }
}