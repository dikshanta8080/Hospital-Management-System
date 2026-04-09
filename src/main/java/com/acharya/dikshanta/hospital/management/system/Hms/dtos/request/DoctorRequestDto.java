package com.acharya.dikshanta.hospital.management.system.Hms.dtos.request;

import com.acharya.dikshanta.hospital.management.system.Hms.validator.EmailValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DoctorRequestDto {
    @NotEmpty(message = "Please provide valid name")
    private String name;

    @NotEmpty(message = "Please provide valid specialization")
    private String specialization;
    
    @EmailValidator
    private String email;

    @NotBlank(message = "Please provide valid password")
    private String password;
}
