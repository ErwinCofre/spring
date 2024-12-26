package com.spring.spring.interfaces;

import com.spring.spring.dtos.CategoriaDTO;
import com.spring.spring.dtos.ProductoDTO;

import java.util.List;

public interface CategoriaService {

    List<CategoriaDTO> obtenerCategorias();
}
