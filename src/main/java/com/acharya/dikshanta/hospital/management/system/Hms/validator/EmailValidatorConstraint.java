package com.acharya.dikshanta.hospital.management.system.Hms.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class EmailValidatorConstraint implements ConstraintValidator<EmailValidator, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.endsWith("@gmail.com");
    }
}
