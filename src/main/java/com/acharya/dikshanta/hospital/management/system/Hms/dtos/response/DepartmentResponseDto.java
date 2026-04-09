package com.acharya.dikshanta.hospital.management.system.Hms.dtos.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DepartmentResponseDto {
    private Long id;
    private String name;
}
