package com.acharya.dikshanta.hospital.management.system.Hms.repository;

import com.acharya.dikshanta.hospital.management.system.Hms.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
