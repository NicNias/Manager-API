package com.app.manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AdmDto(
        @NotBlank @NotNull UUID id,
        @NotBlank @NotNull String name,
        @NotBlank @NotNull String email,
        @NotBlank @NotNull String password
) {
}
