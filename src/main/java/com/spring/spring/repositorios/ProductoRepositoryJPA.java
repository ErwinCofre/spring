package com.spring.spring.repositorios;

import com.spring.spring.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepositoryJPA extends JpaRepository<Producto, Integer> {

//    //Metodos custom
    List<Producto> findByNombre(String nombre);
//
//    List<Producto> findByPrecioGreaterThan(double precio);
//
//    List<Producto> findByNombreAndPrecio(String nombre, Double precio);
//
//    List<Producto> findAllOrderByPrecioDesc();
//
//    //Metodos query JPA
//    @Query("Select p from producto p  where p.nombre=:nombre")
//    List<Producto> BuscarByNombre(@Param("nombre") String nombre);
//
    //query nativas
    @Query(value = "Select * from producto   where nombre=:nombre",nativeQuery = true)
    List<Producto> BuscarByNombreNativo(@Param("nombre") String nombre);



}
