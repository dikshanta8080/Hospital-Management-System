package com.acharya.dikshanta.hospital.management.system.Hms.mappers.response;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.DoctorResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Doctor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DoctorResponseMapper implements Function<Doctor, DoctorResponseDto> {
    @Override
    public DoctorResponseDto apply(Doctor doctor) {
        return DoctorResponseDto
                .builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .email(doctor.getUser().getEmail())
                .specialization(doctor.getSpecialization())
                .role(doctor.getUser().getRole())
                .build();
    }
}
