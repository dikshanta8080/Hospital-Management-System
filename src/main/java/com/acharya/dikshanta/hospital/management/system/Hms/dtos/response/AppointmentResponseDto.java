package com.acharya.dikshanta.hospital.management.system.Hms.dtos.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AppointmentResponseDto {
    private Long id;
    private String patientName;
    private String doctorName;
    private LocalDateTime appointmentTime;
    private String reason;
    private LocalDateTime createdAt;

}
