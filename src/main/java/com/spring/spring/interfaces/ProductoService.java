package com.spring.spring.interfaces;

import com.spring.spring.dtos.ProductoDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoDTO> obtenerProductos();

    ProductoDTO guardarProducto(ProductoDTO nuevoProductoDTO);

    List<ProductoDTO> buscarProductoByNombre(String nombre);

}
