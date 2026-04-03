package com.acharya.dikshanta.hospital.management.system.Hms.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "insurance", uniqueConstraints = {
        @UniqueConstraint(name = "policy_number_unique_constraint", columnNames = {"policyNumber"})
})

public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String policyNumber;

    @Column(nullable = false, length = 110)
    private String provider;

    @Column(nullable = false)
    private LocalDate validUntil;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @OneToOne(mappedBy = "insurance")
    private Patient patient;


}
