package com.acharya.dikshanta.hospital.management.system.Hms.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AppointmentRequestDto {
    @NotNull(message = "Please provide valid patient id")
    private Long patientId;
    @NotNull(message = "Please provide valid doctor id")
    private Long doctorId;
    @NotNull(message = "please provide valid time")
    private LocalDateTime appointmentTime;
    @NotEmpty(message = "Please provide non empty reason")
    private String reason;
}
