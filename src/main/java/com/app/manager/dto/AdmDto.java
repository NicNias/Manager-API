package com.app.manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AdmDto {
    private UUID id;
    private String name;
    private String email;
    private String password;

    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String password() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }
}
