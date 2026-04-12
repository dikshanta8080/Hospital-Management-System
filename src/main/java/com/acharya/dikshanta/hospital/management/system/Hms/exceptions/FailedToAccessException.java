package com.acharya.dikshanta.hospital.management.system.Hms.exceptions;

public class FailedToAccessException extends RuntimeException {
    public FailedToAccessException(String message) {
        super(message);
    }
}
