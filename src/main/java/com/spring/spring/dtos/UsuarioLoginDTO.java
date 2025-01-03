package com.spring.spring.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

public class UsuarioLoginDTO {


    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    private String username;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;

    @NotEmpty(message = "ROl no puede estar vacío")
    private String role;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
