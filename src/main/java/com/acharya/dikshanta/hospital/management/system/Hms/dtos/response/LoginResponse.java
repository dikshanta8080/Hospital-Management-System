package com.acharya.dikshanta.hospital.management.system.Hms.dtos.response;

import com.acharya.dikshanta.hospital.management.system.Hms.types.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LoginResponse {
    private String token;
    private Role role;
    private Long profileId;

}
