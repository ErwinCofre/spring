package com.spring.spring.services;

import com.spring.spring.dtos.CategoriaDTO;
import com.spring.spring.interfaces.CategoriaService;
import com.spring.spring.models.Categoria;
import com.spring.spring.repositorios.CategoriaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepositoryJPA categoriaRepositoryJPA;

    @Override
    public List<CategoriaDTO> obtenerCategorias() {
        List<Categoria> categoriaList = this.categoriaRepositoryJPA.findAll();
        return categoriaList.stream()
                .map(categoria -> new CategoriaDTO(categoria,true))
                .collect(Collectors.toList());
    }
}
