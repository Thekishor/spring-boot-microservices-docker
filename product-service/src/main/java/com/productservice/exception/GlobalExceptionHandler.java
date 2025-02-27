package com.productservice.exception;

import com.productservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException exception){
        String message = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(UnauthorizedAccessException exception){
        String message = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldname = ((FieldError)error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldname,message);
                }
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}