package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.AppointmentRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.AppointmentResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.exceptions.DoctorDoesNotExistsException;
import com.acharya.dikshanta.hospital.management.system.Hms.mappers.response.AppointmentResponseMapper;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Appointment;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Doctor;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.AppointmentRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.DepartmentRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.DoctorRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppointmentService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final AppointmentResponseMapper responseMapper;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public AppointmentResponseDto createAppointment(AppointmentRequestDto requestDto) {
        Patient patient = patientRepository.findById(requestDto.getPatientId()).orElseThrow(() -> new EntityNotFoundException("Patient with this id does not exists"));
        Doctor doctor = doctorRepository.findById(requestDto.getDoctorId()).orElseThrow(() -> new DoctorDoesNotExistsException("Doctor does not exists exception"));
        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .reason(requestDto.getReason())
                .appointmentTime(requestDto.getAppointmentTime())
                .build();
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return responseMapper.apply(savedAppointment);

    }
}
