package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.model.User;
import com.acharya.dikshanta.hospital.management.system.Hms.model.UserPrincipal;
import com.acharya.dikshanta.hospital.management.system.Hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User with this username not found"));
        return new UserPrincipal(user);
    }
}
