package com.acharya.dikshanta.hospital.management.system.Hms;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.PatientRegistrationRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.PatientRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.service.PatientService;
import com.acharya.dikshanta.hospital.management.system.Hms.types.BloodGroupType;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTest {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientService patientService;

    private Pageable getPageble(int pageNo, int pageSize, String sortBy, Sort.Direction sortDir) {
        Sort sort = Sort.by(sortDir, sortBy);
        return PageRequest.of(pageNo, pageSize, sort);

    }

    private void savePatient() {
        PatientRegistrationRequestDto p8 = PatientRegistrationRequestDto.builder()
                .name("Nisha Shrestha")
                .birthDate(LocalDate.of(2003, 2, 25))
                .email("nisha.shrestha@example.com")
                .password("pass1234")
                .gender(Gender.FEMALE)
                .bloodGroup(BloodGroupType.O_POSITIVE)
                .build();

        patientService.createPatient(p8);
    }

    @Test
    public void testPatient() {
//        savePatient();
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(System.out::println);

    }
}
