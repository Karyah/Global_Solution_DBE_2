package com.fiap.gs.MHealth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.gs.MHealth.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	Usuario findByEmailUsuario(String email);
	Usuario findByCpfUsuario(String cpf);
} 
