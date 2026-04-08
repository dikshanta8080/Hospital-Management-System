package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.PatientRegistrationRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.GetPatientDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.PatientRegistrationResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.exceptions.PatientAlreadyExistsException;
import com.acharya.dikshanta.hospital.management.system.Hms.mappers.request.PatientRegisterReqMapper;
import com.acharya.dikshanta.hospital.management.system.Hms.mappers.response.PatientRegisterResMapper;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRegisterReqMapper reqMapper;
    private final PatientRegisterResMapper resMapper;

    @Transactional
    public PatientRegistrationResponseDto createPatient(PatientRegistrationRequestDto requestDto) {
        if (patientRepository.existsByEmail(requestDto.getEmail())) {
            throw new PatientAlreadyExistsException("Patient with this email already exists");
        }
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Patient patient = reqMapper.apply(requestDto);
        patient.setPassword(encodedPassword);
        Patient savedPatient = patientRepository.save(patient);
        return resMapper.apply(savedPatient);

    }

    public List<GetPatientDto> getAllPatients() {
        return patientRepository.getAllPatient();
    }

}
