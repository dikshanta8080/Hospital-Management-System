package com.acharya.dikshanta.hospital.management.system.Hms.security;

import com.acharya.dikshanta.hospital.management.system.Hms.handlers.CustomAccessDeniedHandler;
import com.acharya.dikshanta.hospital.management.system.Hms.handlers.CustomAuthenticationEntryPoint;
import com.acharya.dikshanta.hospital.management.system.Hms.service.CustomUserDetailsService;
import com.acharya.dikshanta.hospital.management.system.Hms.types.Permissions;
import com.acharya.dikshanta.hospital.management.system.Hms.utils.ApiEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.acharya.dikshanta.hospital.management.system.Hms.types.Permissions.*;
import static com.acharya.dikshanta.hospital.management.system.Hms.utils.ApiEndpoints.*;
import static com.acharya.dikshanta.hospital.management.system.Hms.utils.ApiEndpoints.DEPARTMENT_ASSIGN_DOCTOR;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String HEALTH_ROUTE = "/api/v1/health/check";
    private static final String AUTH_ROUTE = "/api/v1/auth/**";
    private static final String BASE_URL = "/api/v1";
    private final JwtFilter jwtFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(req ->
                        req.requestMatchers(HEALTH_ROUTE).permitAll()
                                .requestMatchers(
                                        "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/swagger-resources/**",
                                        "/webjars/**"
                                ).permitAll()

                                .requestMatchers(HEALTH_CHECK)
                                .permitAll()

                                .requestMatchers(AUTH_LOGIN)
                                .permitAll()

                                .requestMatchers(PATIENT_CREATE_API).permitAll()
                                .requestMatchers(HttpMethod.GET, PATIENT_GET_ALL, PATIENT_GET_BY_ID).hasAuthority(PATIENT_READ.getPermissionName())
                                .requestMatchers(HttpMethod.DELETE, PATIENT_DELETE_API).hasAuthority(PATIENT_DELETE.getPermissionName())
                                .requestMatchers(HttpMethod.POST, DOCTOR_ADD).hasAuthority(DOCTOR_CREATE.getPermissionName())
                                .requestMatchers(HttpMethod.DELETE, DOCTOR_DELETE_API).hasAuthority(DOCTOR_DELETE.getPermissionName())
                                .requestMatchers(HttpMethod.POST, DEPARTMENT_ADD_API, DEPARTMENT_ADD_MULTIPLE).hasAuthority(DEPARTMENT_CREATE.getPermissionName())
                                .requestMatchers(HttpMethod.PUT, DEPARTMENT_ASSIGN_DOCTOR, DEPARTMENT_ASSIGN_HOD_API).hasAnyAuthority(Permissions.DEPARTMENT_ASSIGN_HOD.getPermissionName(), Permissions.DEPARTMENT_ASSIGN_DOCTOR.getPermissionName())
                                .requestMatchers(HttpMethod.POST, ApiEndpoints.APPOINTMENT_CREATE).hasAuthority(Permissions.APPOINTMENT_CREATE.getPermissionName())
                                .requestMatchers(HttpMethod.PUT, ApiEndpoints.APPOINTMENT_CANCEL).hasAuthority(Permissions.APPOINTMENT_CANCEL.getPermissionName())
                                .requestMatchers(HttpMethod.DELETE, DEPARTMENT_REMOVE_HOD_API).hasAuthority(DEPARTMENT_DELETE.getPermissionName())
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        corsConfiguration.setAllowedMethods(List.of("GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
