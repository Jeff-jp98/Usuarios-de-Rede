package com.dpema.cadastrodeusuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dpema.cadastrodeusuario.model.Usuario;
import com.dpema.cadastrodeusuario.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@RequestMapping("/cadastro")
	public String form(Model model){
		model.addAttribute("u", new Usuario());
		return "form";
	}
	
	@PostMapping("/salvar")
	public String cadastrar(Usuario u) {
		repository.save(u);
		return "redirect:/usuario/cadastro";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("usuarios", repository.findAll());
		return "lista";
	}
}
