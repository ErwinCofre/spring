package com.spring.spring.models;


import com.spring.spring.dtos.CategoriaDTO;
import jakarta.persistence.*;


@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;



    public Categoria() {
    }

    public Categoria(CategoriaDTO categoriaDTO) {
        this.id = categoriaDTO.getId();
        this.nombre = categoriaDTO.getNombre();
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
        return "Categoria(id=" + this.getId() + ", nombre=" + this.getNombre() +")";
    }
}
