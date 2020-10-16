package com.dpema.cadastrodeusuario.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

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
	
	static final Runtime run = Runtime.getRuntime();
	static Process pro;
	static BufferedReader read;
	
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

	@RequestMapping("/ipconfig")
	public String ipconfig(Model model) throws Exception {
		String[] cmds = {
	            "ipconfig"
	        };

	        try {
	            ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
	                String.join("& ", cmds));

	            builder.redirectErrorStream(true);

	            Process p = builder.start();

	            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            String line;

	            while (true) {
	                line = r.readLine();
	                if (line == null) {
	                    break;
	                }

	                model.addAttribute(line);
	                System.out.println(line);
	            }
	        } catch(Exception e) {
	            System.err.println(e);
	        }
		return "resposta";
	}
}