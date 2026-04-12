package com.acharya.dikshanta.hospital.management.system.Hms.controller;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.ApiResponse;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.AppointmentRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.AppointmentResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AppointmentResponseDto>> makeAppointment(@RequestBody AppointmentRequestDto requestDto) {
        AppointmentResponseDto responseDto = appointmentService.createAppointment(requestDto);
        ApiResponse<AppointmentResponseDto> apiResponse = ApiResponse.<AppointmentResponseDto>builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Appointment has been made")
                .responseObject(responseDto)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PutMapping("/cancel/{appointmentId}")
    public ResponseEntity<ApiResponse<AppointmentResponseDto>> cancelAppointment(@PathVariable Long appointmentId) {
        AppointmentResponseDto appointmentResponseDto = appointmentService.cancelAppointment(appointmentId);
        ApiResponse<AppointmentResponseDto> apiResponse = ApiResponse.<AppointmentResponseDto>builder()
                .httpStatus(HttpStatus.OK)
                .message("Appointment cancelled")
                .responseObject(appointmentResponseDto)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }
}
