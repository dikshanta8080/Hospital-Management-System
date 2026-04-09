package com.acharya.dikshanta.hospital.management.system.Hms.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.debug("Request reached the logging filter!");

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        log.info("Incoming request at {} in time {}", request.getRequestURI(), LocalDateTime.now());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
