package com.acharya.dikshanta.hospital.management.system.Hms.controller;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.ApiResponse;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.LoginRequest;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.LoginResponse;
import com.acharya.dikshanta.hospital.management.system.Hms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse login = authService.login(loginRequest);
        ApiResponse<LoginResponse> apiResponse = ApiResponse.<LoginResponse>builder()
                .httpStatus(HttpStatus.OK)
                .message("Login Successful")
                .responseObject(login)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
