package com.acharya.dikshanta.hospital.management.system.Hms.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private HttpStatus httpStatus;
    private String message;
    private T responseObject;
}
