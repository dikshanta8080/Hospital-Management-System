package com.acharya.dikshanta.hospital.management.system.Hms.types;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum Role {

    PATIENT(List.of(
            Permissions.PATIENT_READ,
            Permissions.PATIENT_UPDATE,
            Permissions.DEPARTMENT_READ,
            Permissions.DOCTOR_READ,
            Permissions.APPOINTMENT_RESCHEDULE,
            Permissions.APPOINTMENT_CANCEL,
            Permissions.APPOINTMENT_VIEW,
            Permissions.APPOINTMENT_CREATE
    )),

    DOCTOR(List.of(
            Permissions.DOCTOR_READ,
            Permissions.DOCTOR_UPDATE,
            Permissions.PATIENT_READ,
            Permissions.DEPARTMENT_READ,
            Permissions.APPOINTMENT_RESCHEDULE,
            Permissions.APPOINTMENT_CANCEL,
            Permissions.APPOINTMENT_VIEW,
            Permissions.APPOINTMENT_CREATE
    )),


    ADMIN(List.of(
            Permissions.PATIENT_READ,
            Permissions.PATIENT_UPDATE,
            Permissions.PATIENT_DELETE,

            Permissions.DOCTOR_CREATE,
            Permissions.DOCTOR_READ,
            Permissions.DOCTOR_UPDATE,
            Permissions.DOCTOR_DELETE,

            Permissions.DEPARTMENT_CREATE,
            Permissions.DEPARTMENT_READ,
            Permissions.DEPARTMENT_UPDATE,
            Permissions.DEPARTMENT_DELETE,
            Permissions.DEPARTMENT_ASSIGN_HOD,
            Permissions.DEPARTMENT_ASSIGN_DOCTOR,

            Permissions.APPOINTMENT_RESCHEDULE,
            Permissions.APPOINTMENT_CANCEL,
            Permissions.APPOINTMENT_VIEW


    ));

    private final List<Permissions> permissions;

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}