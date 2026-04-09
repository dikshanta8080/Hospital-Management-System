package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

}
