package com.acharya.dikshanta.hospital.management.system.Hms.utils;

import com.acharya.dikshanta.hospital.management.system.Hms.types.BloodGroupType;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDate;

@ConfigurationProperties(prefix = "utils")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Utils {
    private Admin admin;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Admin {
        private String name;
        private String email;
        private Gender gender;
        private String password;
        private LocalDate birthDate;
        private BloodGroupType bloodGroup;
    }
}
