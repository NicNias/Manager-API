package com.app.manager.services;

import com.app.manager.dto.AdmDto;
import com.app.manager.entity.AdmEntity;
import com.app.manager.mappers.AdmMapper;
import com.app.manager.repository.AdmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmService {
    private final AdmMapper admMapper;
    private final AdmRepository admRepository;

    public AdmDto createAdm(AdmDto admDto) {
        admRepository.findByEmail(admDto.email()).ifPresent(admEntity -> {
            throw new RuntimeException("Email ja cadastrado no Sistema");
        });
        AdmEntity admEntity = admRepository.save(admMapper.toModel(admDto));
        return admMapper.toDto(admEntity);
    }
}
