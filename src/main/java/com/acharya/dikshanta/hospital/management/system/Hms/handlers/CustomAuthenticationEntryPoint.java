package com.acharya.dikshanta.hospital.management.system.Hms.handlers;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.ApiResponse;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionClass(authException.getClass().getName())
                .errorMessage(authException.getMessage())
                .errorMap(null)
                .build();
        ApiResponse<ExceptionResponse> apiResponse = ApiResponse.<ExceptionResponse>builder()
                .httpStatus(httpStatus)
                .message("Unauthorized")
                .responseObject(exceptionResponse)
                .build();
        objectMapper.writeValue(response.getOutputStream(), apiResponse);
    }
}
