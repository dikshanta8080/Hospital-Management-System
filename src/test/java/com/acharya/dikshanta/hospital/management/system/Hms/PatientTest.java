package com.acharya.dikshanta.hospital.management.system.Hms;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.PatientBasicDto;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Patient;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.PatientRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.types.BloodGroupType;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PatientTest {
    @Autowired
    private PatientRepository patientRepository;

    private void savePatient() {
        List<Patient> patients = new ArrayList<>();

        patients.add(Patient.builder().name("Pushpa Kamal Dahal").birthDate(LocalDate.of(1954, 12, 11)).email("prachanda@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.A_POSITIVE).build());
        patients.add(Patient.builder().name("K P Sharma Oli").birthDate(LocalDate.of(1952, 2, 22)).email("oli@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.O_POSITIVE).build());
        patients.add(Patient.builder().name("Sher Bahadur Deuba").birthDate(LocalDate.of(1946, 6, 13)).email("deuba@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.B_POSITIVE).build());
        patients.add(Patient.builder().name("Madhav Kumar Nepal").birthDate(LocalDate.of(1953, 3, 12)).email("madhav@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.AB_POSITIVE).build());
        patients.add(Patient.builder().name("Baburam Bhattarai").birthDate(LocalDate.of(1954, 6, 18)).email("baburam@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.O_NEGATIVE).build());
        patients.add(Patient.builder().name("Ram Chandra Poudel").birthDate(LocalDate.of(1944, 10, 6)).email("poudel@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.A_POSITIVE).build());
        patients.add(Patient.builder().name("Gagan Thapa").birthDate(LocalDate.of(1976, 7, 19)).email("gagan@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.B_NEGATIVE).build());
        patients.add(Patient.builder().name("Bishnu Prasad Paudel").birthDate(LocalDate.of(1959, 11, 15)).email("bishnu@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.O_POSITIVE).build());
        patients.add(Patient.builder().name("Barsha Man Pun").birthDate(LocalDate.of(1954, 10, 14)).email("barsha@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.AB_NEGATIVE).build());
        patients.add(Patient.builder().name("Pradeep Kumar Gyawali").birthDate(LocalDate.of(1960, 4, 22)).email("gyawali@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.A_POSITIVE).build());

        patients.add(Patient.builder().name("Mahanta Thakur").birthDate(LocalDate.of(1942, 1, 16)).email("mahanta@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.B_POSITIVE).build());
        patients.add(Patient.builder().name("Upendra Yadav").birthDate(LocalDate.of(1959, 9, 12)).email("upendra@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.O_NEGATIVE).build());
        patients.add(Patient.builder().name("Rabi Lamichhane").birthDate(LocalDate.of(1974, 9, 18)).email("rabi@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.AB_POSITIVE).build());
        patients.add(Patient.builder().name("Rajendra Lingden").birthDate(LocalDate.of(1966, 9, 5)).email("lingden@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.B_NEGATIVE).build());
        patients.add(Patient.builder().name("Chitra Bahadur KC").birthDate(LocalDate.of(1942, 6, 15)).email("chitra@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.O_POSITIVE).build());
        patients.add(Patient.builder().name("Narayan Kaji Shrestha").birthDate(LocalDate.of(1963, 3, 10)).email("narayan@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.A_POSITIVE).build());
        patients.add(Patient.builder().name("Pampha Bhusal").birthDate(LocalDate.of(1960, 11, 21)).email("pampha@example.com").gender(Gender.FEMALE).bloodGroup(BloodGroupType.AB_NEGATIVE).build());
        patients.add(Patient.builder().name("Shashanka Koirala").birthDate(LocalDate.of(1949, 3, 24)).email("shashanka@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.B_POSITIVE).build());
        patients.add(Patient.builder().name("Arzu Rana Deuba").birthDate(LocalDate.of(1962, 2, 26)).email("arzu@example.com").gender(Gender.FEMALE).bloodGroup(BloodGroupType.O_NEGATIVE).build());
        patients.add(Patient.builder().name("Prakash Sharan Mahat").birthDate(LocalDate.of(1959, 1, 6)).email("prakash@example.com").gender(Gender.MALE).bloodGroup(BloodGroupType.A_POSITIVE).build());
        List<Patient> allSavedPatients = patientRepository.saveAll(patients);
        allSavedPatients.forEach(System.out::println);
    }

    @Test
    public void testPatient() {
//        savePatient();
//        List<Patient> patients = patientRepository.findAll();
//        List<String> patientNames = patients.stream()
//                .map(Patient::getName)
//                .filter(name -> name.startsWith("P"))
//                .toList();

//        patientNames.forEach(System.out::println);

//        System.out.println(patientNames.stream().count());

//        List<Patient> patients = patientRepository.findByIdIn(List.of(1L, 2L, 3L, 4L));
//        patients.forEach(System.out::println);

//        Patient patient = patientRepository.findByEmailAndName("deuba@example.com", "Sher Bahadur Deuba");
//        System.out.println(patient);

//        List<PatientDto> patientDtos = patientRepository.findAllPatients();
//        patientDtos.forEach(System.out::println);

        PatientBasicDto patientBasicDtos = patientRepository.getBasicDetailsOfPatient("deuba@example.com");
        System.out.println(patientBasicDtos);
        List<Patient> patients = patientRepository.findAllPatientStartingWithLetter("P" + "%");
        patients.forEach(System.out::println);
    }
}
