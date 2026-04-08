package com.acharya.dikshanta.hospital.management.system.Hms.loaders;

import com.acharya.dikshanta.hospital.management.system.Hms.model.User;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.UserRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Role;
import com.acharya.dikshanta.hospital.management.system.Hms.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminLoader implements CommandLineRunner {
    private final Utils utils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Utils.Admin admin = utils.getAdmin();
        if (!userRepository.existsByEmail(admin.getEmail())) {
            User user = User.builder()
                    .email(admin.getEmail())
                    .password(passwordEncoder.encode(admin.getPassword()))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(user);
        }

    }
}
