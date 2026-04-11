package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.PatientRegistrationRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.GetPatientDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.PatientRegistrationResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.exceptions.PatientAlreadyExistsException;
import com.acharya.dikshanta.hospital.management.system.Hms.mappers.request.PatientRegisterReqMapper;
import com.acharya.dikshanta.hospital.management.system.Hms.mappers.response.PatientRegisterResMapper;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import com.acharya.dikshanta.hospital.management.system.Hms.model.User;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.PatientRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.UserRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final UserRepository userRepository;

    @Transactional
    public PatientRegistrationResponseDto createPatient(PatientRegistrationRequestDto requestDto) {
        if (patientRepository.existsByUserEmail(requestDto.getEmail())
                || userRepository.existsByEmail(requestDto.getEmail())) {
            throw new PatientAlreadyExistsException("Patient with this email already exists");
        }
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Patient patient = reqMapper.apply(requestDto);
        User user = User.builder()
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .role(Role.PATIENT)
                .build();

        User savedUser = userRepository.save(user);
        patient.setUser(savedUser);
        Patient savedPatient = patientRepository.save(patient);
        return resMapper.apply(savedPatient);

    }

    public List<GetPatientDto> getAllPatients(Pageable pageable) {
        return patientRepository.getAllPatient(pageable).stream().toList();
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new EntityNotFoundException("Patient with this id does not exists");
        }
        patientRepository.deleteById(id);
    }

    public PatientRegistrationResponseDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Patient with this id does not exists"));
        return resMapper.apply(patient);
    }

}
