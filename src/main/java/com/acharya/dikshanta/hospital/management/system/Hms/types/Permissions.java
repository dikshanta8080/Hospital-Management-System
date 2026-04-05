package com.acharya.dikshanta.hospital.management.system.Hms.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permissions {
    PATIENT_CREATE("patient:create"),
    PATIENT_READ("patient:read"),
    PATIENT_UPDATE("patient:update"),
    PATIENT_DELETE("patient:delete"),

    DOCTOR_CREATE("doctor:create"),
    DOCTOR_READ("doctor:read"),
    DOCTOR_UPDATE("doctor:update"),
    DOCTOR_DELETE("doctor:delete"),

    RECEPTIONIST_CREATE("receptionist:create"),
    RECEPTIONIST_READ("receptionist:read"),
    RECEPTIONIST_UPDATE("receptionist:update"),
    RECEPTIONIST_DELETE("receptionist:delete"),

    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete");
    @Getter
    private final String permissionName;

}
