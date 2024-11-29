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

@Service
@RequiredArgsConstructor
public class AdmService {
    private final AdmMapper admMapper;
    private final AdmRepository admRepository;

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
}
