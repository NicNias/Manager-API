package com.app.manager.controllers;

import com.app.manager.dto.AdmDto;
import com.app.manager.dto.AdmInfoDto;
import com.app.manager.dto.AuthenticationRequestDto;
import com.app.manager.dto.SessionDto;
import com.app.manager.mappers.AdmMapper;
import com.app.manager.repository.AdmRepository;
import com.app.manager.services.AdmService;
import com.app.manager.services.AuthenticationService;
import com.app.manager.services.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adm")
@Slf4j
public class AdmController {
    private final AdmMapper admMapper;
    private final AdmService admService;
    private final AdmRepository admRepository;
    private final TokenService tokenService;
    private final AuthenticationService authenticationService;

    @PostMapping("/create")
    public ResponseEntity<AdmDto> saveAdm(@RequestBody @Valid AdmDto admDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(admService.createAdm(admDto));
    }

    @PostMapping("/login")
    public SessionDto login(@RequestBody AuthenticationRequestDto request) {
        String token = authenticationService.authenticate(request);
        return new SessionDto(token);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<AdmInfoDto>> getAllAdms() {
        return ResponseEntity.status(HttpStatus.OK).body(admService.findAll());
    }

    @GetMapping("/findone/{id}")
    public ResponseEntity<AdmInfoDto> getOneAdm(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(admService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdmDto> updateAdm(@PathVariable UUID id, @RequestBody @Valid AdmDto admDto) {
        AdmDto adm = admService.updateAdm(id, admDto);
        return ResponseEntity.status(HttpStatus.OK).body(adm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdm(@PathVariable UUID id) {
        admService.deleteAdm(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
