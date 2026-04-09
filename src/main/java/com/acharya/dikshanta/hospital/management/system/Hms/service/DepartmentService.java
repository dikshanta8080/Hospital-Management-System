package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.AddDepartmentRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.AddDepartmentResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.exceptions.DepartmentAlreadyExistsException;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Department;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Transactional
    public AddDepartmentResponseDto addDepartment(AddDepartmentRequestDto requestDto) {
        if (departmentRepository.existsByName(requestDto.getName())) {
            throw new DepartmentAlreadyExistsException("This department Already exists");
        }
        Department department = Department.builder()
                .name(requestDto.getName())
                .build();
        Department savedDepartment = departmentRepository.save(department);
        return AddDepartmentResponseDto.builder()
                .id(savedDepartment.getId())
                .name(savedDepartment.getName())
                .build();

    }
}
