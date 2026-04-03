package com.acharya.dikshanta.hospital.management.system.Hms.model;

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

    @ManyToMany
    @JoinTable(
            name = "department_doctor",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")

    )
    private Set<Doctor> doctors = new HashSet<>();

}
