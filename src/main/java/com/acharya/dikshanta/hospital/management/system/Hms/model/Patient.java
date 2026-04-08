package com.acharya.dikshanta.hospital.management.system.Hms.model;

import com.acharya.dikshanta.hospital.management.system.Hms.types.BloodGroupType;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "\"patient\"",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unique_constraint", columnNames = {"email"}),
                @UniqueConstraint(name = "name_dob_constraint", columnNames = {"name", "birthDate"})
        })
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private LocalDate birthDate;

    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne
    @JoinColumn(name = "insurance_id", nullable = true)
    private Insurance insurance;

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference
    @ToString.Exclude
    private List<Appointment> appointments;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public void addAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
            appointment.setPatient(this);
        }
    }

    public void removeAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.remove(appointment);
            appointment.setPatient(null);
        }
    }

}
