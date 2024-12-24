package com.spring.spring.repositorios;


import com.spring.spring.models.Producto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Producto> obtenerLista(){
        String sql="SELECT * FROM PRODUCTO";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Producto.class));
    }

    public void guardarProducto(Producto producto){
        String sql="INSERT INTO producto(nombre,descripcion,precio,en_stock) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,producto.getNombre(),producto.getDescripcion(),producto.getPrecio(),producto.isEnStock());
        //queryForObject
    }
}
