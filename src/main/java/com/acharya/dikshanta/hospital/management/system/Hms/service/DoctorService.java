package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.DoctorRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.DoctorResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.mappers.response.DoctorResponseMapper;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Doctor;
import com.acharya.dikshanta.hospital.management.system.Hms.model.User;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.DepartmentRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.DoctorRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.UserRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Role;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorResponseMapper doctorResponseMapper;
    private final PasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;

    public DoctorResponseDto createDoctor(DoctorRequestDto requestDto) {
        if (doctorRepository.existsByUserEmail(requestDto.getEmail()) || userRepository.existsByEmail(requestDto.getEmail())) {
            throw new EntityExistsException("Doctor with this email already exists");
        }
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = User.builder()
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .role(Role.DOCTOR)
                .build();
        Doctor doctor = Doctor.builder()
                .name(requestDto.getName())
                .specialization(requestDto.getSpecialization())
                .user(user)
                .build();
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorResponseMapper.apply(doctor);
    }


}
