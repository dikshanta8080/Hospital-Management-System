package com.acharya.dikshanta.hospital.management.system.Hms.dtos.request;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddMultipleDepartmentRequestDto {
    private List<DepartmentRequestDto> departments;
}
