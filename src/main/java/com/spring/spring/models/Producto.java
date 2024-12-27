package com.spring.spring.models;

import com.spring.spring.dtos.ProductoDTO;
import jakarta.persistence.*;



//
//    ALTER TABLE producto
//    ADD COLUMN categoria_id BIGINT,
//    ADD CONSTRAINT fk_productos_categorias
//    FOREIGN KEY (categoria_id)
//    REFERENCES categoria(id);
/***
 * Modelo que representa abstracción de un producto
 * @author erwin
 */
@Entity
@Table(name = "producto")
public class Producto {
    /***
     * identificador unico de producto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "enStock", nullable = false)
    private boolean enStock;

    //donde tenga la FK en la tabla dejaré la anotacion ManyToOne
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;


    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, double precio, boolean enStock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.enStock = enStock;
    }

    public Producto(ProductoDTO productoDTO) {
        this.id = productoDTO.getId();
        this.nombre = productoDTO.getNombre();
        this.descripcion = productoDTO.getDescripcion();
        this.precio = productoDTO.getPrecio();
        this.enStock = productoDTO.isEnStock();
        this.categoria = new Categoria(productoDTO.getCategoria());

    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

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
