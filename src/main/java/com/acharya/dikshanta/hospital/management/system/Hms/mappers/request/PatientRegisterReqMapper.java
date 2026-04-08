package com.acharya.dikshanta.hospital.management.system.Hms.mappers.request;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.PatientRegistrationRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PatientRegisterReqMapper implements Function<PatientRegistrationRequestDto, Patient> {
    @Override
    public Patient apply(PatientRegistrationRequestDto requestDto) {
        return Patient
                .builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .gender(requestDto.getGender())
                .birthDate(requestDto.getBirthDate())
                .bloodGroup(requestDto.getBloodGroup())
                .build();
    }
}