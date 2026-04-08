package com.acharya.dikshanta.hospital.management.system.Hms.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidatorConstraint.class)
public @interface EmailValidator {
    String message() default "Please provide valid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
