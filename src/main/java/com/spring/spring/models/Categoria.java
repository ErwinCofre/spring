package com.spring.spring.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.spring.dtos.CategoriaDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;
//CREATE TABLE categoria (
//        id SERIAL PRIMARY KEY,
//        nombre VARCHAR(100) UNIQUE NOT NULL
//);

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;


    /// traer productos relacionados
//    @OneToMany(mappedBy = "categoria")
//    List<Producto> productoList;


    public Categoria() {
    }

    public Categoria(CategoriaDTO categoriaDTO) {
        this.id = categoriaDTO.getId();
        this.nombre = categoriaDTO.getNombre();
//        this.productoList = categoriaDTO.getProductoDTOList()
//                .stream()
//                .map(productoDTO -> new Producto(productoDTO))
//                .collect(Collectors.toList());
    }


//    public List<Producto> getProductoList() {
//        return productoList;
//    }
//
//    public void setProductoList(List<Producto> productoList) {
//        this.productoList = productoList;
//    }

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
        return "Categoria(id=" + this.getId() + ", nombre=" + this.getNombre() + ")";
    }
}
