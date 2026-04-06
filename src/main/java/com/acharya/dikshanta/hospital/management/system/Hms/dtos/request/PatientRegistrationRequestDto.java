package com.acharya.dikshanta.hospital.management.system.Hms.dtos.request;

import com.acharya.dikshanta.hospital.management.system.Hms.types.BloodGroupType;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Gender;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PatientRegistrationRequestDto {
    private String name;
    private LocalDate birthDate;
    private String email;
    private Gender gender;
    private BloodGroupType bloodGroup;
    private String policyNumber;
    private String provider;
    private LocalDate validUntil;
}
