package com.acharya.dikshanta.hospital.management.system.Hms.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permissions {

    PATIENT_READ("patient:read"),
    PATIENT_UPDATE("patient:update"),
    PATIENT_DELETE("patient:delete"),

    DOCTOR_CREATE("doctor:create"),
    DOCTOR_READ("doctor:read"),
    DOCTOR_UPDATE("doctor:update"),
    DOCTOR_DELETE("doctor:delete"),

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),

    DEPARTMENT_CREATE("department:create"),
    DEPARTMENT_READ("department:read"),
    DEPARTMENT_UPDATE("department:update"),
    DEPARTMENT_DELETE("department:delete"),
    DEPARTMENT_ASSIGN_HOD("department:assign:hod"),
    DEPARTMENT_ASSIGN_DOCTOR("department:assign:doctor"),

    APPOINTMENT_CREATE("appointment:create"),
    APPOINTMENT_VIEW("appointment:view"),
    APPOINTMENT_CANCEL("appointment:cancel"),
    APPOINTMENT_RESCHEDULE("appointment:reschedule");

    private final String permissionName;


}