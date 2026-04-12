package com.acharya.dikshanta.hospital.management.system.Hms.mappers.response;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.AppointmentResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Appointment;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AppointmentResponseMapper implements Function<Appointment, AppointmentResponseDto> {
    @Override
    public AppointmentResponseDto apply(Appointment appointment) {
        return AppointmentResponseDto
                .builder()
                .id(appointment.getId())
                .patientName(appointment.getPatient().getName())
                .doctorName(appointment.getDoctor().getName())
                .reason(appointment.getReason())
                .appointmentTime(appointment.getAppointmentTime())
                .appointmentStatus(appointment.getAppointmentStatus())
                .createdAt(appointment.getCreatedAt())
                .build();


    }
}
