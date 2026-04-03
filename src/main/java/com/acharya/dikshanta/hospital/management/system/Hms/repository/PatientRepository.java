package com.acharya.dikshanta.hospital.management.system.Hms.repository;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.PatientBasicDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.PatientDto;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import com.acharya.dikshanta.hospital.management.system.Hms.projections.PatientDtoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT new com.acharya.dikshanta.hospital.management.system.Hms.dtos.PatientBasicDto" +
            "(p.name," +
            "p.email)" +
            " FROM Patient p WHERE p.email=:email")
    PatientBasicDto getBasicDetailsOfPatient(@Param(value = "email") String email);

    boolean existsByEmail(String email);

    boolean existsByNameAndEmail(String name, String email);

    boolean existsByEmailIn(List<String> emails);

    Optional<Patient> findByName(String name);

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByNameAndEmail(String name, String email);

    List<Patient> findByIdIn(List<Long> ids);

    List<Patient> findByNameStartingWith(String reg);

    @Query("SELECT p FROM Patient  p WHERE p.name=:name AND p.email=:email")
    Patient findByEmailAndName(@Param(value = "email") String email, @Param(value = "name") String name);

    @Query("SELECT new com.acharya.dikshanta.hospital.management.system.Hms.dtos.PatientDto(\n" +
            "    p.id,\n" +
            "    p.name,\n" +
            "    p.birthDate,\n" +
            "    p.email,\n" +
            "    p.gender,\n" +
            "    p.bloodGroup\n" +
            ") FROM Patient p")
    List<PatientDto> findAllPatients();

    @Query(value = "SELECT * FROM Patient p WHERE p.name LIKE :regex", nativeQuery = true)
    List<Patient> findAllPatientStartingWithLetter(@Param(value = "regex") String regex);

    @Query("SELECT p.name AS name,p.email AS email FROM Patient  p")
    List<PatientDtoProjection> listAllPatients();
}
