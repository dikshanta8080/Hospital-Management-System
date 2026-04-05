package com.acharya.dikshanta.hospital.management.system.Hms.types;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum Role {

    PATIENT(List.of(
            Permissions.PATIENT_CREATE,
            Permissions.PATIENT_READ,
            Permissions.PATIENT_UPDATE,
            Permissions.PATIENT_DELETE
    )),

    DOCTOR(List.of(
            Permissions.DOCTOR_CREATE,
            Permissions.DOCTOR_READ,
            Permissions.DOCTOR_UPDATE,
            Permissions.DOCTOR_DELETE
    )),

    RECEPTIONIST(List.of(
            Permissions.RECEPTIONIST_CREATE,
            Permissions.RECEPTIONIST_READ,
            Permissions.RECEPTIONIST_UPDATE,
            Permissions.RECEPTIONIST_DELETE
    )),

    ADMIN(List.of(
            Permissions.ADMIN_CREATE,
            Permissions.ADMIN_READ,
            Permissions.ADMIN_UPDATE,
            Permissions.ADMIN_DELETE
    ));

    private final List<Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(permissions1 -> new SimpleGrantedAuthority(permissions1.getPermissionName()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}