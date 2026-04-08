package com.acharya.dikshanta.hospital.management.system.Hms.dtos.response;

import com.acharya.dikshanta.hospital.management.system.Hms.types.BloodGroupType;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPatientDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private Gender gender;
    private BloodGroupType bloodGroup;
}
