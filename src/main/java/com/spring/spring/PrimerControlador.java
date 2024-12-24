package com.spring.spring;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primero")
public class PrimerControlador {

    @GetMapping("/mensaje")
    public String getMensaje(){
        return "Este es mi primer EndPoint";
    }

    @GetMapping("/contacto")
    public String getContacto(){
        return "Este es mi contacto";
    }


}
