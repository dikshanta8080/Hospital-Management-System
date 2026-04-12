package com.acharya.dikshanta.hospital.management.system.Hms.dtos.response;

import com.acharya.dikshanta.hospital.management.system.Hms.types.AppointmentStatus;
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
    private AppointmentStatus appointmentStatus;
    private LocalDateTime createdAt;


}
