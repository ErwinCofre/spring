package com.spring.spring.services;

import com.spring.spring.models.UsuarioLogin;
import com.spring.spring.repositorios.UsuarioLoginRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioLoginService implements UserDetailsService {

    @Autowired
    private UsuarioLoginRepositoryJPA usuarioLoginRepository;

    /**
     * metodo que junta la informacion necesario para que
     * SS pueda generar autenticacion
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //ir a buscar al usuario
        UsuarioLogin usuarioLogin = this.usuarioLoginRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado en login"));
        //set del rol de usuario encontrado
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuarioLogin.getRole());

        //return
        return new User(usuarioLogin.getUsername(), usuarioLogin.getPassword(), Collections.singletonList(authority));


    }
}