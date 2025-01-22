package com.app.manager.services;

import com.app.manager.dto.AuthenticationRequestDto;
import com.app.manager.entity.AdmEntity;
import com.app.manager.exceptions.CustomException;
import com.app.manager.repository.AdmRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AdmRepository admRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public String authenticate(AuthenticationRequestDto request) {
        AdmEntity admEntity = getAdm(request.getEmail());
        if(!passwordEncoder.matches(request.getPassword(), admEntity.getPassword())) {
            throw new CustomException("E-mail ou Senha Inválidos!", HttpStatus.UNAUTHORIZED, null);
        }
        return tokenService.generateToken(admEntity.getId().toString());
    }

    public AdmEntity getAdm(String email) {
        return admRepository.findByEmail(email).orElseThrow(() -> {
            String errorMessage = "Adm não encontrado para o email: " + email;
            System.err.println(errorMessage);
            throw new CustomException("Adm não encontrado!", HttpStatus.NOT_FOUND, null);
        });
    }
}
