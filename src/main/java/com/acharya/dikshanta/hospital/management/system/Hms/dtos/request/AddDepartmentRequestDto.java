package com.acharya.dikshanta.hospital.management.system.Hms.dtos.request;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "unique_name", columnNames = {"name"})
})
public class AddDepartmentRequestDto {
    @Column(length = 100, nullable = false)
    private String name;
}
