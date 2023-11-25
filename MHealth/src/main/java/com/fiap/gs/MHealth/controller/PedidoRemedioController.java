package com.fiap.gs.MHealth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.gs.MHealth.dto.DadosPedidoRemedio;
import com.fiap.gs.MHealth.model.Pedido;
import com.fiap.gs.MHealth.model.Usuario;
import com.fiap.gs.MHealth.repositories.PedidoRepository;
import com.fiap.gs.MHealth.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pedido-remedio")
public class PedidoRemedioController {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public  ModelAndView formularioRemedio() {
		ModelAndView mv = new ModelAndView("pedidoRemedio/formulario");
		return mv;	
		
	}
	
	@PostMapping("/novo")
	@Transactional
	public  ModelAndView novoRemedio(@Valid DadosPedidoRemedio dados) {
		Usuario usuario = usuarioRepository.findByCpfUsuario(dados.cpfUsuario());
		Pedido pedido = new Pedido(dados);
		pedido.setUsuario(usuario);
		repository.save(pedido);
		ModelAndView mv = new ModelAndView("redirect:/usuario/home");
		return mv;	
		
	}
}
