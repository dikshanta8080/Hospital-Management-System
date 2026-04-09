package com.acharya.dikshanta.hospital.management.system.Hms.repository;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.GetPatientDto;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByUserEmail(String email);

    Optional<Patient> findByUserEmail(String email);

    @Query("SELECT new com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.GetPatientDto(" +
            "p.id, " +
            "p.name, " +
            "p.birthDate, " +
            "p.user.email, " +
            "p.gender, " +
            "p.bloodGroup," +
            "p.user.role" +
            ") " +
            "FROM Patient p")
    Page<GetPatientDto> getAllPatient(Pageable pageable);

}
