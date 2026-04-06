package com.acharya.dikshanta.hospital.management.system.Hms.mappers;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.PatientRegistrationResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PatientToPatientRegistrationResponseMapper implements Function<Patient, PatientRegistrationResponseDto> {
    @Override
    public PatientRegistrationResponseDto apply(Patient patient) {
        return PatientRegistrationResponseDto
                .builder()
                .name(patient.getName())
                .email(patient.getEmail())
                .birthDate(patient.getBirthDate())
                .gender(patient.getGender())
                .bloodGroup(patient.getBloodGroup())
                .policyNumber(patient.getInsurance().getPolicyNumber())
                .validUntil(patient.getInsurance().getValidUntil())
                .build();
    }
}
