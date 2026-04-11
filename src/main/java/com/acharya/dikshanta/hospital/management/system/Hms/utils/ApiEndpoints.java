package com.acharya.dikshanta.hospital.management.system.Hms.utils;

public class ApiEndpoints {

    // Auth routes
    public static final String AUTH_LOGIN = "/api/v1/auth/login";

    // Patient routes
    public static final String PATIENT_CREATE_API = "/api/v1/patient/create";
    public static final String PATIENT_GET_ALL = "/api/v1/patient/getall";
    public static final String PATIENT_GET_BY_ID = "/api/v1/patient/get/{id}";
    public static final String PATIENT_DELETE_API = "/api/v1/patient/delete/{id}";

    // Doctor routes
    public static final String DOCTOR_ADD = "/api/v1/doctor/add";
    public static final String DOCTOR_DELETE_API = "/api/v1/doctor/delete/{doctorId}";

    // Department routes
    public static final String DEPARTMENT_ADD_API = "/api/v1/department/add";
    public static final String DEPARTMENT_ADD_MULTIPLE = "/api/v1/department/addmultiple";
    public static final String DEPARTMENT_ASSIGN_DOCTOR = "/api/v1/department/assigndoctor";
    public static final String DEPARTMENT_ASSIGN_HOD_API = "/api/v1/department/assign/hod";
    public static final String DEPARTMENT_REMOVE_HOD_API = "/api/v1/department/removehod/{departmentId}";

    // Appointment routes
    public static final String APPOINTMENT_CREATE = "/api/v1/appointment/create";

    // Health monitoring route
    public static final String HEALTH_CHECK = "/api/v1/health/check";

    private ApiEndpoints() {

    }
}
