package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.AddMultipleDepartmentRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.DepartmentRequestDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.AddMultipleDepartmentResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.DepartmentResponseDto;
import com.acharya.dikshanta.hospital.management.system.Hms.exceptions.DepartmentAlreadyExistsException;
import com.acharya.dikshanta.hospital.management.system.Hms.exceptions.DepartmentDoesNotExistsException;
import com.acharya.dikshanta.hospital.management.system.Hms.exceptions.DoctorDoesNotExistsException;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Department;
import com.acharya.dikshanta.hospital.management.system.Hms.model.Doctor;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.DepartmentRepository;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public DepartmentResponseDto addDepartment(DepartmentRequestDto requestDto) {
        if (departmentRepository.existsByName(requestDto.getName())) {
            throw new DepartmentAlreadyExistsException("This department Already exists");
        }
        Department department = Department.builder()
                .name(requestDto.getName())
                .build();
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentResponseDto.builder()
                .id(savedDepartment.getId())
                .name(savedDepartment.getName())
                .build();

    }

    public AddMultipleDepartmentResponseDto addMultipleDepartment(AddMultipleDepartmentRequestDto requestDto) {
        List<Department> savedDepartments = getDepartments(requestDto);
        List<DepartmentResponseDto> departments = savedDepartments.stream()
                .map(department -> {
                    return DepartmentResponseDto.builder()
                            .id(department.getId())
                            .name(department.getName())
                            .build();

                }).toList();
        return AddMultipleDepartmentResponseDto
                .builder().responseDto(departments)
                .build();


    }

    private @NonNull List<Department> getDepartments(AddMultipleDepartmentRequestDto requestDto) {
        List<DepartmentRequestDto> requestDtos = requestDto.getDepartments();
        List<Department> savedDepartments = new ArrayList<>();
        requestDtos.forEach(dep -> {
            if (!departmentRepository.existsByName(dep.getName())) {
                Department department = Department.builder()
                        .name(dep.getName())
                        .build();
                Department savedDepartment = departmentRepository.save(department);
                savedDepartments.add(savedDepartment);
            }
        });
        return savedDepartments;
    }

    public void assignHod(Long doctorId, Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentDoesNotExistsException("Department with provided id does not exists"));

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new DoctorDoesNotExistsException(("Doctor with provided id does not exists")));
        department.setHeadOfDepartment(doctor);
        departmentRepository.save(department);

    }

    /* Yesko controller banauna baki xa*/
    public List<DepartmentResponseDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(department -> {
                    return DepartmentResponseDto.builder()
                            .id(department.getId())
                            .name(department.getName())
                            .build();
                }
        ).toList();
    }

    /* Yesko controller banauna baki xa*/
    public DepartmentResponseDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Department with this id does not exists"));
        return DepartmentResponseDto.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}
