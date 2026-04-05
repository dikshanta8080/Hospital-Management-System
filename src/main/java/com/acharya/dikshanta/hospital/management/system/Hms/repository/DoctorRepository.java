package com.acharya.dikshanta.hospital.management.system.Hms.repository;

import com.acharya.dikshanta.hospital.management.system.Hms.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByEmail(String email);

    Optional<Doctor> findByEmail(String email);

}
