package com.acharya.dikshanta.hospital.management.system.Hms.repository;

import com.acharya.dikshanta.hospital.management.system.Hms.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.id=:appointmentId AND a.patient.id=:patientId")
    Optional<Appointment> getAppointmentByAppointmentIdAndPatientId(@Param(value = "appointmentId") Long id, @Param(value = "patientId") Long patientId);

    @Query("SELECT a FROM Appointment a WHERE a.id=:appointmentId AND a.doctor.id=:doctorId")
    Optional<Appointment> getAppointmentByAppointmentIdAndDoctorId(@Param(value = "appointmentId") Long id, @Param(value = "doctorId") Long doctorId);

}
