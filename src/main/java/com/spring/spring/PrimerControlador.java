package com.spring.spring;


import com.spring.spring.dtos.UsuarioLoginDTO;
import com.spring.spring.services.UsuarioLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class PrimerControlador {



    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied"; // Vista para acceso denegado
    }


}
