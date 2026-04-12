package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.AppointmentRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.AppointmentResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.exceptions.DoctorDoesNotExistsException;
import com.acharya.dikshanta.hospital.management.system.Hms.exceptions.FailedToAccessException;
import com.acharya.dikshanta.hospital.management.system.Hms.mappers.response.AppointmentResponseMapper;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Appointment;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Doctor;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import com.acharya.dikshanta.hospital.management.system.Hms.model.User;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.AppointmentRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.DepartmentRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.DoctorRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.PatientRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.types.AppointmentStatus;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Role;
import com.acharya.dikshanta.hospital.management.system.Hms.utils.GetLoggedInUser;
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
    private final GetLoggedInUser getLoggedInUser;

    @Transactional
    public AppointmentResponseDto createAppointment(AppointmentRequestDto requestDto) {
        Patient patient = patientRepository.findById(requestDto.getPatientId()).orElseThrow(() -> new EntityNotFoundException("Patient with this id does not exists"));
        Doctor doctor = doctorRepository.findById(requestDto.getDoctorId()).orElseThrow(() -> new DoctorDoesNotExistsException("Doctor does not exists exception"));
        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .reason(requestDto.getReason())
                .appointmentStatus(AppointmentStatus.SCHEDULED)
                .appointmentTime(requestDto.getAppointmentTime())
                .build();
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return responseMapper.apply(savedAppointment);

    }

    public AppointmentResponseDto cancelAppointment(Long appointmentId) {

        User loggedInUser = getLoggedInUser.getLoggedInUser();
        Appointment appointment;
        if (loggedInUser.getRole() == Role.PATIENT) {
            appointment = appointmentRepository.getAppointmentByAppointmentIdAndPatientId(appointmentId, loggedInUser.getPatient().getId()).orElseThrow(() -> new EntityNotFoundException("Appointment with this id does not exists"));
        } else if (loggedInUser.getRole() == Role.DOCTOR) {

            appointment = appointmentRepository.getAppointmentByAppointmentIdAndDoctorId(appointmentId, loggedInUser.getDoctor().getId()).orElseThrow(() -> new EntityNotFoundException("Appointment with this id does not exists"));
        } else {
            throw new FailedToAccessException("Admin can not cancel the appointment");
        }
        if (appointment.getAppointmentStatus() == AppointmentStatus.CANCELLED) {
            throw new RuntimeException("Appointment Already Cancelled");
        }
        if (appointment.getAppointmentStatus() == AppointmentStatus.COMPLETED) {
            throw new RuntimeException("Completed appointment can not be cancelled");
        }
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return responseMapper.apply(savedAppointment);

    }
}
