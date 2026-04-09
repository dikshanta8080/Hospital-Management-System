package com.acharya.dikshanta.hospital.management.system.Hms.dtos.response;

import com.acharya.dikshanta.hospital.management.system.Hms.types.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DoctorResponseDto {
    private Long id;
    private String name;
    private String specialization;
    private Role role;

}
