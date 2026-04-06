package com.acharya.dikshanta.hospital.management.system.Hms.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = EmailValidatorConstraint.class)
public @interface EmailValidator {
    public String message() default "please enter valid email";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
