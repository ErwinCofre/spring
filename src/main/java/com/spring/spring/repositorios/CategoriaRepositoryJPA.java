package com.spring.spring.repositorios;

import com.spring.spring.models.Categoria;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepositoryJPA extends JpaRepository<Categoria, Integer> {

    @EntityGraph(attributePaths = {"productoList"})
    @Override
    List<Categoria> findAll();

}
