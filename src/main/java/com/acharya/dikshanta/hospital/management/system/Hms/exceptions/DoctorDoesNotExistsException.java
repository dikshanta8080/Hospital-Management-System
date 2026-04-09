package com.acharya.dikshanta.hospital.management.system.Hms.exceptions;

public class DoctorDoesNotExistsException extends RuntimeException {
    public DoctorDoesNotExistsException(String message) {
        super(message);
    }
}
