package com.app.manager.services;

import com.app.manager.dto.AdmDto;
import com.app.manager.entity.AdmEntity;
import com.app.manager.exceptions.CustomException;
import com.app.manager.mappers.AdmMapper;
import com.app.manager.repository.AdmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdmService {
    private final AdmMapper admMapper;
    private final AdmRepository admRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryp

    public AdmDto createAdm(AdmDto admDto) {
        admRepository.findByEmail(admDto.email()).ifPresent(admEntity -> {
            throw new CustomException("Email ja cadastrado no Sistema", HttpStatus.CONFLICT, null);
        });
        AdmEntity admEntity = admRepository.save(admMapper.toModel(admDto));
        return admMapper.toDto(admEntity);
    }

    public List<AdmDto> findAll() {
        List<AdmEntity> adms = admRepository.findAll();
        if (adms.isEmpty()) {
            throw new CustomException("Nenhum Adm encontrado", HttpStatus.NOT_FOUND, null);
        }
        return admMapper.ListAdmDto(adms);
    }

    public AdmDto findById(UUID id) {
        AdmEntity adm = admRepository.findById(id).orElseThrow(() -> {
            throw new CustomException("Adm não está cadastrado", HttpStatus.NOT_FOUND, null);
        });
        return admMapper.toDto(adm);
    }
}
