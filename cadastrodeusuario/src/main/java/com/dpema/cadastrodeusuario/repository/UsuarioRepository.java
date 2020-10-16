package com.dpema.cadastrodeusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpema.cadastrodeusuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
}
