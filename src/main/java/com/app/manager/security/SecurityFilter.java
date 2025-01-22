package com.app.manager.security;

import com.app.manager.exceptions.AuthorizationHeaderException;
import com.app.manager.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException, AuthenticationException {
        if (request.getRequestURI().startsWith("/swagger")
            || request.getRequestURI().startsWith("/v3/api-docs")
            || request.getRequestURI().startsWith("/adm/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = this.recoverToken(request);
        log.info(token);
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = String.valueOf(request.getHeader("x-auth"));
        if (authHeader == null || authHeader.isEmpty()) {
            throw new AuthorizationHeaderException();
        }
        return authHeader;
    }
}
