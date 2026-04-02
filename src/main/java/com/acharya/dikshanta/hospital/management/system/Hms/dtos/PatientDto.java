package com.acharya.dikshanta.hospital.management.system.Hms.dtos;

import com.acharya.dikshanta.hospital.management.system.Hms.types.BloodGroupType;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Gender;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDto {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private Gender gender;
    private BloodGroupType bloodGroup;
}
