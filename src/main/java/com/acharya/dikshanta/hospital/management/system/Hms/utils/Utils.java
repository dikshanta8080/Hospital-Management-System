package com.acharya.dikshanta.hospital.management.system.Hms.utils;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "utils")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
@Setter
public class Utils {
    private Admin admin;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Admin {
        private String email;
        private String password;
    }
}
