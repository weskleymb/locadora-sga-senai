package br.senai.rn.locadora.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.rn.locadora.models.Categoria;
import br.senai.rn.locadora.services.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public String index(Model model) {
		Categoria categoria = new Categoria();
		List<Categoria> categorias = service.obterTodos();
		model.addAttribute("categoria", categoria);
		model.addAttribute("categorias", categorias);
		return "categoria/index";
	}
	
	@PostMapping
	public String salvar(Categoria categoria) {
		service.salvar(categoria);
		return "redirect:/categoria";
	}
	
}
