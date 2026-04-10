package com.acharya.dikshanta.hospital.management.system.Hms.controller;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.ApiResponse;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.DoctorRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.DoctorResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> deleteDoctor(@PathVariable Long doctorId) {
        DoctorResponseDto doctorResponseDto = doctorService.deleteDoctor(doctorId);
        ApiResponse<DoctorResponseDto> apiResponse = ApiResponse.<DoctorResponseDto>builder()
                .httpStatus(HttpStatus.OK)
                .message("Deletion Successful")
                .responseObject(doctorResponseDto)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
