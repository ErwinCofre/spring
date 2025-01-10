package com.spring.spring.controllers;

import com.spring.spring.dtos.CategoriaDTO;
import com.spring.spring.dtos.ProductoDTO;
import com.spring.spring.dtos.UsuarioLoginDTO;
import com.spring.spring.interfaces.CategoriaService;
import com.spring.spring.interfaces.ProductoService;
import com.spring.spring.jwt.JwtTokenService;
import com.spring.spring.services.UsuarioLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/rest/producto")
public class ProductoRestController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping("/categoria/lista")
    public List<CategoriaDTO> mostrarCategorias() {
        return this.categoriaService.obtenerCategorias();
    }


    @GetMapping("/lista")
    public ResponseEntity<?> mostrarLista() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    Map.of(
                            "result", productoService.obtenerProductos()
                            , "codigo", HttpStatus.OK
                    )
            );
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "error", "Recurso no encontrado",
                            "codigo", HttpStatus.NOT_FOUND,
                            "detalle", e.getMessage()
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of(
                            "Error", "Error al obtener servicio",
                            "Codigo", HttpStatus.INTERNAL_SERVER_ERROR,
                            "detalle", e.getMessage()
                    ));

        }
    }


    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@RequestBody ProductoDTO productoDTO) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.productoService.guardarProducto(productoDTO));

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "error", "Error al guardar",
                            "codigo", HttpStatus.BAD_REQUEST,
                            "detalle", e.getMessage()
                    )
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of(
                            "Error", "Error al obtener servicio",
                            "Codigo", HttpStatus.INTERNAL_SERVER_ERROR,
                            "detalle", e.getMessage()
                    ));
        }
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
