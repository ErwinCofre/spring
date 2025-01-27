package com.spring.spring.services;


import com.spring.spring.dtos.ProductoDTO;
import com.spring.spring.interfaces.ProductoService;
import com.spring.spring.models.Producto;
import com.spring.spring.repositorios.ProductoRepository;
import com.spring.spring.repositorios.ProductoRepositoryJPA;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    /*  integracion JDBC
        @Autowired
        private ProductoRepository productoRepository;
    */
    @Autowired
    private Validator validator;

    @Autowired
    private ProductoRepositoryJPA productoRepositoryJPA;

    @Override
    public List<ProductoDTO> obtenerProductos() {

//        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        User userAuth= (User) principal;
//        System.out.println("Usuario que esta en sesion");
//        System.out.println(userAuth.getUsername());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());

        List<Producto> productos = this.productoRepositoryJPA.findAll();

        if (productos.isEmpty()) {
            throw new NoSuchElementException("No se encontraron productos");
        }
        return productos.stream()
                .map(producto -> new ProductoDTO(producto, true))
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
        Producto nuevoProducto = new Producto(nuevoProductoDTO);

//        this.validarEntidad(nuevoProducto);

        this.productoRepositoryJPA.save(nuevoProducto);
        return nuevoProductoDTO;
    }

    @Override
    public List<ProductoDTO> buscarProductoByNombre(String nombre) {
        return this.productoRepositoryJPA.findByNombre(nombre).stream()
                .map(producto -> new ProductoDTO(producto))
                .collect(Collectors.toList());
    }

    /***
     * Metodo para validar validaciones por atributoen  paso de DTO a entidad
     * @param producto
     */
    private void validarEntidad(Producto producto) {
        Set<ConstraintViolation<Producto>> validacion = validator.validate(producto);
        if (!validacion.isEmpty()) {
            throw new ConstraintViolationException(validacion);
        }
    }
    // spring.jpa.properties.javax.persistence.validation.mode=callback

    /***
     * Metodo para validar validaciones por atributoen  paso de Entidad a DTO
     * @param productoDTO
     */
    private void validarDTO(ProductoDTO productoDTO) {
        Set<ConstraintViolation<ProductoDTO>> validacion = validator.validate(productoDTO);
        if (!validacion.isEmpty()) {
            throw new ConstraintViolationException(validacion);
        }
    }
}
