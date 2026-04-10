package com.acharya.dikshanta.hospital.management.system.Hms.controller;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.ApiResponse;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.AddMultipleDepartmentRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.AssignDoctorToDepartmentRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.AssignHodRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.DepartmentRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.AddMultipleDepartmentResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.DepartmentResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> addDepartment(@RequestBody DepartmentRequestDto requestDto) {
        DepartmentResponseDto departmentResponseDto = departmentService.addDepartment(requestDto);
        ApiResponse<DepartmentResponseDto> apiResponse = ApiResponse.<DepartmentResponseDto>builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Department created successfully")
                .responseObject(departmentResponseDto)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PostMapping("/addmultiple")
    public ResponseEntity<ApiResponse<AddMultipleDepartmentResponseDto>> addMultipleDepartment(@RequestBody AddMultipleDepartmentRequestDto requestDto) {
        AddMultipleDepartmentResponseDto addMultipleDepartmentResponseDto = departmentService.addMultipleDepartment(requestDto);
        ApiResponse<AddMultipleDepartmentResponseDto> apiResponse = ApiResponse.<AddMultipleDepartmentResponseDto>builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Departments added successfully")
                .responseObject(addMultipleDepartmentResponseDto)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @PutMapping("/assign/hod")
    public ResponseEntity<ApiResponse<String>> assignHodToDepartment(@RequestBody AssignHodRequestDto requestDto) {
        departmentService.assignHod(requestDto.getDoctorId(), requestDto.getDepartmentId());
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .httpStatus(HttpStatus.OK)
                .message("Assigned HOD")
                .responseObject("Head of department assigned successfully")
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());


    }

    @PostMapping("/assigndoctor")
    public ResponseEntity<ApiResponse<String>> assignDoctorToDepartment(@RequestBody AssignDoctorToDepartmentRequestDto requestDto) {
        departmentService.assignDoctor(requestDto);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .httpStatus(HttpStatus.OK)
                .message("Assigned Doctor")
                .responseObject("Doctor assigned successfully")
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @DeleteMapping("/removehod/{departmentId}")
    public ResponseEntity<ApiResponse<String>> removeHod(@PathVariable Long departmentId) {
        departmentService.removeHod(departmentId);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .httpStatus(HttpStatus.OK)
                .message("Hod Removed")
                .responseObject("Hod removed successfully successfully")
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
