package com.techtalent.playlist.exceptionhandler;

import com.techtalent.playlist.exception.PlayListNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlayListNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            PlayListNotFoundException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
