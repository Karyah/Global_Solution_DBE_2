package com.fiap.gs.MHealth.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.gs.MHealth.dto.DadosUsuario;
import com.fiap.gs.MHealth.model.Usuario;
import com.fiap.gs.MHealth.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("cadastro");
		return mv;
	}
	
	@PostMapping("/novo")
	@Transactional
	public ModelAndView novo(@Valid DadosUsuario dados) {
		Usuario usuario = new Usuario(dados);
		
		usuario.setSenhaUsuario(encoder.encode(dados.senhaUsuario()));
		repository.save(usuario);
		
		ModelAndView mv = new ModelAndView("redirect:/pedidos");
		return mv;
	}	

	
}
