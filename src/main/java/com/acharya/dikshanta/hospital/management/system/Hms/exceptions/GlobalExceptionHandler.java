package com.acharya.dikshanta.hospital.management.system.Hms.exceptions;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.ApiResponse;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> handleAllExceptions(Exception e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        httpStatus = e instanceof AccessDeniedException ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionClass(e.getClass().getSimpleName())
                .errorMessage(e.getMessage())
                .errorMap(null)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .httpStatus(httpStatus)
                .message("Exception Occurred!")
                .responseObject(exceptionResponse)
                .build();
        return new ResponseEntity<>(apiResponse, httpStatus);


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        fieldErrors.forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionClass(e.getClass().getSimpleName())
                .errorMessage("Method Argument Not Valid Exception Occurred")
                .errorMap(errorMap)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Exception Occurred!")
                .responseObject(exceptionResponse)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
