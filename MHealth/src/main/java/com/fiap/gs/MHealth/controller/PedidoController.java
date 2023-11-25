package com.fiap.gs.MHealth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.gs.MHealth.dto.DadosPedidoRemedio;
import com.fiap.gs.MHealth.model.Pedido;
import com.fiap.gs.MHealth.repositories.PedidoRepository;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository repository;
	
	@GetMapping
	public ModelAndView pedidos() {
		ModelAndView mv = new ModelAndView("pedidos");
		List<Pedido> pedidos = repository.findAll();
		mv.addObject("pedidos", pedidos);
		return mv;
	}
	

	
}
