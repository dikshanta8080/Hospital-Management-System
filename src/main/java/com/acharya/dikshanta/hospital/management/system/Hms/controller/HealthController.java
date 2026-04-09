package com.acharya.dikshanta.hospital.management.system.Hms.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping("/check")
    public ResponseEntity<String> getHealthStatus(HttpServletRequest req, HttpServletResponse res) {
        String sessionId = req.getSession().getId();
        String message = "Tomcat is running in port 8080 with session id " + sessionId;
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
}
