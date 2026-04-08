package com.acharya.dikshanta.hospital.management.system.Hms.repository;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.GetPatientDto;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByEmail(String email);

    Optional<Patient> findByEmail(String email);

    @Query(value =
            "SELECT new com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.GetPatientDto(" +
                    "p.id," +
                    "p.name," +
                    "p.birthDate," +
                    "p.email," +
                    "p.gender," +
                    "p.bloodGroup" +
                    ") FROM Patient p", nativeQuery = false)
    List<GetPatientDto> getAllPatient();

}
