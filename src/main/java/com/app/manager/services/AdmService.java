package com.app.manager.services;

import com.app.manager.dto.AdmDto;
import com.app.manager.dto.AdmInfoDto;
import com.app.manager.entity.AdmEntity;
import com.app.manager.exceptions.CustomException;
import com.app.manager.mappers.AdmMapper;
import com.app.manager.repository.AdmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdmService {
    private final AdmMapper admMapper;
    private final AdmRepository admRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdmDto createAdm(AdmDto admDto) {
        admRepository.findByEmail(admDto.email()).ifPresent(admEntity -> {
            throw new CustomException("Email ja cadastrado no Sistema", HttpStatus.CONFLICT, null);
        });
        String encrytedPassword = passwordEncoder.encode(admDto.password());
        AdmEntity newAdm = admMapper.toModel(admDto);
        newAdm.setPassword(encrytedPassword);
        admRepository.save(newAdm);
        return admMapper.toDto(newAdm);
    }

    public List<AdmInfoDto> findAll() {
        List<AdmEntity> adms = admRepository.findAll();
        if (adms.isEmpty()) {
            throw new CustomException("Nenhum Adm encontrado", HttpStatus.NOT_FOUND, null);
        }
        return admMapper.ListAdmDto(adms);
    }

    public AdmInfoDto findById(UUID id) {
        AdmEntity adm = admRepository.findById(id).orElseThrow(() -> {
            throw new CustomException("Adm não está cadastrado", HttpStatus.NOT_FOUND, null);
        });
        return admMapper.toInfoDto(adm);
    }

    public AdmDto updateAdm(UUID id, AdmDto admDto) {
        AdmEntity adm = admRepository.findById(id).orElseThrow(() -> {
            throw new CustomException("Adm não está cadastrado", HttpStatus.NOT_FOUND, null);
        });
        if (admDto.password() != null && !admDto.password().isEmpty()) {
            String encryptedPassword = passwordEncoder.encode(admDto.password());
            adm.setPassword(encryptedPassword);
        }
        admMapper.updateEntityFromDto(admDto, adm);
        AdmEntity updateEntity = admRepository.save(adm);
        return admMapper.toDto(updateEntity);
    }

    public void deleteAdm(UUID id) {
        AdmEntity adm = admRepository.findById(id).orElseThrow(() -> {
            throw new CustomException("Adm não está cadastrado", HttpStatus.NOT_FOUND, null);
        });
        admRepository.delete(adm);
    }
}
