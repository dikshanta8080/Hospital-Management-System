package com.acharya.dikshanta.hospital.management.system.Hms.controller;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.ApiResponse;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.DoctorRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.DoctorResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> addDoctor(@RequestBody DoctorRequestDto requestDto) {
        DoctorResponseDto doctor = doctorService.createDoctor(requestDto);
        ApiResponse<DoctorResponseDto> apiResponse = ApiResponse.<DoctorResponseDto>builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Doctor Created Successfully")
                .responseObject(doctor)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
