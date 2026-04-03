package com.acharya.dikshanta.hospital.management.system.Hms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "email_constraint", columnNames = {"email"})
})
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(length = 100, nullable = false)
    private String specialization;
    @Column(nullable = false, length = 50)
    private String email;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "doctor")
    @JsonManagedReference
    private List<Appointment> appointments;

    @ManyToMany(mappedBy = "doctors")
    private Set<Department> departments = new HashSet<>();

    public void addAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
            appointment.setDoctor(this);
        }
    }

    public void removeAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.remove(appointment);
            appointment.setDoctor(null);
        }
    }
}
