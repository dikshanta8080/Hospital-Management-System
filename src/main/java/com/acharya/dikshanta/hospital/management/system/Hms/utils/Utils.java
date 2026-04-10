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
    private Jwt jwt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Admin {
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Jwt {
        private Integer expiry;
        private String secret;
    }
}
