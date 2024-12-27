package com.spring.spring.dtos;

import com.spring.spring.models.Categoria;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CategoriaDTO {
    private int id;
    private String nombre;
    private List<ProductoDTO> productoDTOList;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nombre = categoria.getNombre();
//        this.productoDTOList=categoria.getProductoList()
//                .stream()
//                .map(producto-> new ProductoDTO(producto))
//                .collect(Collectors.toList());

    }

    public CategoriaDTO(Categoria categoria, boolean include) {
        this.id = categoria.getId();
        this.nombre = categoria.getNombre();
        if (include){
            this.productoDTOList = categoria.getProductoList()
                    .stream()
                    .map(producto -> new ProductoDTO(producto,false))
                    .collect(Collectors.toList());
        }

    }

    public List<ProductoDTO> getProductoDTOList() {
        return productoDTOList;
    }

    public void setProductoDTOList(List<ProductoDTO> productoDTOList) {
        this.productoDTOList = productoDTOList;
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
