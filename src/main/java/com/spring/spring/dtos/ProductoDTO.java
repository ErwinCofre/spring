package com.spring.spring.dtos;


import com.spring.spring.models.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class ProductoDTO {


    private int id;

    @NotNull(message = "nombre no puede ser null")
    @Size(min = 5, max = 50, message = "largo de nombre debe estar entre 5 y 50")
    @NotBlank(message = "Nombre no puede estar en blanco")
    private String nombre;

    @NotNull
    @Size(min = 5, max = 250)
    private String descripcion;

    @NotNull
    @Positive
    private double precio;

    private boolean enStock;

//    private CategoriaDTO categoriaDTO;

    public ProductoDTO() {

    }

    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.enStock = producto.isEnStock();

    }

    public ProductoDTO(Producto producto, boolean include) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.enStock = producto.isEnStock();
//        if (include) {
//            this.categoriaDTO = producto.getCategoria() == null ? null : new CategoriaDTO(producto.getCategoria(), false);
//        }
    }

//    public CategoriaDTO getCategoria() {
//        return categoriaDTO;
//    }
//
//    public void setCategoria(CategoriaDTO categoriaDTO) {
//        this.categoriaDTO = categoriaDTO;
//    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public double getPrecio() {
        return this.precio;
    }

    public boolean isEnStock() {
        return this.enStock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setEnStock(boolean enStock) {
        this.enStock = enStock;
    }

    public String toString() {
        return "Producto(id=" + this.getId() + ", nombre=" + this.getNombre() + ", descripcion=" + this.getDescripcion() + ", precio=" + this.getPrecio() + ", enStock=" + this.isEnStock() + ")";
    }
}
