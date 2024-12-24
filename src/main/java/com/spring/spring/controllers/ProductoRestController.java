package com.spring.spring.controllers;

import com.spring.spring.dtos.ProductoDTO;
import com.spring.spring.interfaces.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/producto")
public class ProductoRestController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/lista")
    public List<ProductoDTO> mostrarLista() {
        return productoService.obtenerProductos();
    }


    @PostMapping("/guardar")
    public ProductoDTO guardarProducto(@RequestBody ProductoDTO productoDTO) {
        return this.productoService.guardarProducto(productoDTO);
    }
}
