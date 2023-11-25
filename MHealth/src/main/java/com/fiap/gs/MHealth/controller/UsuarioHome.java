package com.fiap.gs.MHealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("usuario/home")
public class UsuarioHome {
	
	 	@GetMapping
	 	public ModelAndView home(){
	 		ModelAndView mv = new ModelAndView("/usuario/home");
	 		return mv;
	 	}
}
