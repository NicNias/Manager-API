package com.app.manager.dto;

import java.util.Map;

public record ExceptionResponseDto(
        String message,
        Map<String, String> erros
) {
}
