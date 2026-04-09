package com.acharya.dikshanta.hospital.management.system.Hms.dtos.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddMultipleDepartmentResponseDto {
    private List<DepartmentResponseDto> responseDto;
}
