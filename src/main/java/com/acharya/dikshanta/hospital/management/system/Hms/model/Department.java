package com.acharya.dikshanta.hospital.management.system.Hms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToOne
    @JoinColumn(name = "hod_id")
    private Doctor headOfDepartment;
    @OneToMany
    @JsonManagedReference
    private Set<Doctor> doctors = new HashSet<>();

    public void addDoctor(Doctor doctor) {
        if (doctor != null) {
            doctors.add(doctor);
            doctor.setDepartment(this);
        }
    }

    public void removeDoctor(Doctor doctor) {
        if (doctor != null) {
            doctors.remove(doctor);
            doctor.setDepartment(null);
        }
    }

}
