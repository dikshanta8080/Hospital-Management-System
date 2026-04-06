package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.PatientRegistrationRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.PatientRegistrationResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientRegistrationResponseDto createPatient(PatientRegistrationRequestDto requestDto) {
        return null;
    }


}
