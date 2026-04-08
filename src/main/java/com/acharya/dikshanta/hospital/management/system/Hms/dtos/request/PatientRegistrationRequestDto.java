package com.acharya.dikshanta.hospital.management.system.Hms.dtos.request;

import com.acharya.dikshanta.hospital.management.system.Hms.types.BloodGroupType;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Gender;
import com.acharya.dikshanta.hospital.management.system.Hms.validator.EmailValidator;
import jakarta.validation.constraints.NotEmpty;
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
    @EmailValidator(message = "Please provide valid email!")
    private String email;
    @NotEmpty(message = "Please provide secure password")
    private String password;
    private Gender gender;
    private BloodGroupType bloodGroup;
}
