package com.app.manager.controllers;

import com.app.manager.dto.AdmDto;
import com.app.manager.mappers.AdmMapper;
import com.app.manager.repository.AdmRepository;
import com.app.manager.services.AdmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AdmController {
    private final AdmMapper admMapper;
    private final AdmService admService;
    private final AdmRepository admRepository;

    @PostMapping("/adm")
    public ResponseEntity<AdmDto> saveAdm(@RequestBody @Valid AdmDto admDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(admService.createAdm(admDto));
    }

    @GetMapping("/adm")
    public ResponseEntity<List<AdmDto>> getAllAdms() {
        return ResponseEntity.status(HttpStatus.OK).body(admService.findAll());
    }

    @GetMapping("/adm/{id}")
    public ResponseEntity<AdmDto> getOneAdm(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(admService.findById(id));
    }
}
