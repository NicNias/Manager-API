package com.app.manager.dto;

import lombok.*;

@Getter
@Setter
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
