package com.backend.portalestuderesponda.dtos;

import jakarta.validation.constraints.NotBlank;

public class UserInsertDTO extends UserDTO {

    @NotBlank(message = "Senha obrigatória")
    private String password;

    public UserInsertDTO() {
    }

    public UserInsertDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}