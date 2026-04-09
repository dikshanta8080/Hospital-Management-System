package com.acharya.dikshanta.hospital.management.system.Hms.repository;

import com.acharya.dikshanta.hospital.management.system.Hms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
