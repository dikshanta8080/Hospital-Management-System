package com.acharya.dikshanta.hospital.management.system.Hms.controller;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.ApiResponse;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.PatientRegistrationRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.GetPatientDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.PatientRegistrationResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PatientRegistrationResponseDto>> createPatient(@RequestBody PatientRegistrationRequestDto registrationRequestDto) {
        PatientRegistrationResponseDto patient = patientService.createPatient(registrationRequestDto);
        ApiResponse<PatientRegistrationResponseDto> apiResponse = ApiResponse.<PatientRegistrationResponseDto>builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Patient Registered Successfully")
                .responseObject(patient)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<GetPatientDto>>> getAllPatients(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir,
            @RequestParam(name = "search", required = false) String search

    ) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("DESC") ?
                Sort.Direction.DESC :
                Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        List<GetPatientDto> allPatients = patientService.getAllPatients(pageable);
        ApiResponse<List<GetPatientDto>> apiResponse = ApiResponse.<List<GetPatientDto>>builder()
                .httpStatus(HttpStatus.OK)
                .message("Patient Parsed Successfully")
                .responseObject(allPatients)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deletePatientById(@PathVariable Long id) {
        patientService.deletePatient(id);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .httpStatus(HttpStatus.OK)
                .message("Patient deleted successfully")
                .responseObject("deletion completed")
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());


    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<PatientRegistrationResponseDto>> getPatientById(@PathVariable Long id) {
        PatientRegistrationResponseDto patientById = patientService.getPatientById(id);
        ApiResponse<PatientRegistrationResponseDto> apiResponse = ApiResponse.<PatientRegistrationResponseDto>builder()
                .httpStatus(HttpStatus.OK)
                .message("Parsed the Patient")
                .responseObject(patientById)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
