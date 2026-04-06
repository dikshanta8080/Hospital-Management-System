package com.acharya.dikshanta.hospital.management.system.Hms.dtos;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ExceptionResponse {
    private String exceptionClass;
    private String errorMessage;
    private Map<String, String> errorMap;
}
