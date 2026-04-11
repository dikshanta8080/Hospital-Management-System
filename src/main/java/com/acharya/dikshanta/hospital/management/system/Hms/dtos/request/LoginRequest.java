package com.acharya.dikshanta.hospital.management.system.Hms.dtos.request;

import com.acharya.dikshanta.hospital.management.system.Hms.validator.EmailValidator;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LoginRequest {
    @EmailValidator
    private String email;
    private String password;
}
