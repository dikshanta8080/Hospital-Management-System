package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.dtos.request.LoginRequest;
import com.acharya.dikshanta.hospital.management.system.Hms.dtos.response.LoginResponse;
import com.acharya.dikshanta.hospital.management.system.Hms.model.User;
import com.acharya.dikshanta.hospital.management.system.Hms.model.UserPrincipal;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public LoginResponse login(LoginRequest loginRequest) {
        LoginResponse loginResponse;
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        assert userPrincipal != null;
        User user = userPrincipal.getUser();
        String token = jwtService.getJwt(userPrincipal);
        if (user.getRole() == Role.PATIENT) {
            Long patientId = user.getPatient().getId();
            loginResponse = LoginResponse.builder()
                    .token(token)
                    .role(Role.PATIENT)
                    .profileId(patientId)
                    .build();
        } else {
            Long doctorId = user.getDoctor().getId();
            loginResponse = LoginResponse.builder()
                    .token(token)
                    .role(Role.DOCTOR)
                    .profileId(doctorId)
                    .build();
        }
        return loginResponse;
    }

}
