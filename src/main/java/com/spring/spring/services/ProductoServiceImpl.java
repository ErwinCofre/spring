package com.spring.spring.services;


import com.spring.spring.dtos.ProductoDTO;
import com.spring.spring.interfaces.ProductoService;
import com.spring.spring.models.Producto;
import com.spring.spring.repositorios.ProductoRepository;
import com.spring.spring.repositorios.ProductoRepositoryJPA;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

/*  integracion JDBC
    @Autowired
    private ProductoRepository productoRepository;
*/

    @Autowired
    private ProductoRepositoryJPA productoRepositoryJPA;

    @Override
    public List<ProductoDTO> obtenerProductos() {
        List<Producto> productos = this.productoRepositoryJPA.findAll();

        if (productos.isEmpty()) {
            throw new IllegalStateException("No se encontraron productos");
        }
        return productos.stream()
                .map(producto -> new ProductoDTO(producto))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductoDTO guardarProducto(ProductoDTO nuevoProductoDTO) {
        //guarda

        //validacion contra informacion de entrada
        if (nuevoProductoDTO.getPrecio() <= 1000) {
            throw new IllegalStateException("Precio no debe ser menor o igual a 1000");
        }

        //validacion contra la informacion de bd
        List<Producto> producto = this.productoRepositoryJPA.findByNombre(nuevoProductoDTO.getNombre());
        if (!producto.isEmpty()) {
            throw new IllegalStateException("Nombre no puede estar duplicado");
        }


        nuevoProductoDTO.setEnStock(true);
        this.productoRepositoryJPA.save(new Producto(nuevoProductoDTO));
        return nuevoProductoDTO;
    }

    @Override
    public List<ProductoDTO> buscarProductoByNombre(String nombre) {
        return this.productoRepositoryJPA.findByNombre(nombre).stream()
                .map(producto -> new ProductoDTO(producto))
                .collect(Collectors.toList());
    }
}
