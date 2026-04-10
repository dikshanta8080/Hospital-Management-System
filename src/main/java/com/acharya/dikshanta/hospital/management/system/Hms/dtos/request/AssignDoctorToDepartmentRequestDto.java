package com.acharya.dikshanta.hospital.management.system.Hms.dtos.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AssignDoctorToDepartmentRequestDto {
    private Long departmentId;
    private Long doctorId;

}
