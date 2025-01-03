package com.spring.spring.models;

import jakarta.persistence.*;


/*

CREATE TABLE usuario_login (
                              id SERIAL PRIMARY KEY,
                              username VARCHAR(50) UNIQUE NOT NULL,
                              password VARCHAR(100) NOT NULL,
                              role VARCHAR(20) NOT NULL
);

INSERT INTO usuario_login (username, password, role)
VALUES ('admin', 'admin123', 'ADMIN'),
      ('user', 'user123', 'USER');

 */
@Entity
@Table(name = "usuario_login")
public class UsuarioLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String role;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
