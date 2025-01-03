package com.spring.spring.controllers;

import com.spring.spring.dtos.CategoriaDTO;
import com.spring.spring.dtos.ProductoDTO;
import com.spring.spring.dtos.UsuarioLoginDTO;
import com.spring.spring.interfaces.CategoriaService;
import com.spring.spring.interfaces.ProductoService;
import com.spring.spring.services.UsuarioLoginService;
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

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    @GetMapping("/categoria/lista")
    public List<CategoriaDTO> mostrarCategorias(){
        return this.categoriaService.obtenerCategorias();
    }


    @GetMapping("/lista")
    public List<ProductoDTO> mostrarLista() {
        return productoService.obtenerProductos();
    }


    @PostMapping("/guardar")
    public ProductoDTO guardarProducto(@RequestBody ProductoDTO productoDTO) {
        return this.productoService.guardarProducto(productoDTO);
    }

    @PostMapping("/user/add")
    public UsuarioLoginDTO add(@Valid @RequestBody UsuarioLoginDTO usuarioDTO) {
        try {
            return usuarioLoginService.addUser(usuarioDTO);
        } catch (IllegalArgumentException e) {
            return new UsuarioLoginDTO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
