package com.acharya.dikshanta.hospital.management.system.Hms.utils;

import com.acharya.dikshanta.hospital.management.system.Hms.model.User;
import com.acharya.dikshanta.hospital.management.system.Hms.model.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetLoggedInUser {
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserPrincipal userPrincipal)) {
            throw new RuntimeException("User not authenticated");
        }
        return userPrincipal.getUser();
    }
}
