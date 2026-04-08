package com.acharya.dikshanta.hospital.management.system.Hms.model;

import com.acharya.dikshanta.hospital.management.system.Hms.types.Role;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false)

    @Enumerated(EnumType.STRING)
    private Role role;
}
