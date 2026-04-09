package com.acharya.dikshanta.hospital.management.system.Hms.exceptions;

public class DepartmentDoesNotExistsException extends RuntimeException {
    public DepartmentDoesNotExistsException(String message) {
        super(message);
    }
}
