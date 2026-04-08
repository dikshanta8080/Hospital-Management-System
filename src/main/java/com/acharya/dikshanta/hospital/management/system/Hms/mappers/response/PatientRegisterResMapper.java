package com.acharya.dikshanta.hospital.management.system.Hms.mappers.response;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.PatientRegistrationResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PatientRegisterResMapper implements Function<Patient, PatientRegistrationResponseDto> {
    @Override
    public PatientRegistrationResponseDto apply(Patient patient) {
        return null;
    }
}
