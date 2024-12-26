package com.spring.spring.dtos;

import com.spring.spring.models.Categoria;
import org.springframework.stereotype.Component;


@Component
public class CategoriaDTO {
    private int id;
    private String nombre;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nombre = categoria.getNombre();
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String toString() {
        return "CategoriaDTO(id=" + this.getId() + ", nombre=" + this.getNombre() + ")";
    }
}
