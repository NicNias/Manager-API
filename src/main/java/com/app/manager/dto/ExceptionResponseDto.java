package com.app.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ExceptionResponseDto {
    String message;
    Map<String, String> erros;
}