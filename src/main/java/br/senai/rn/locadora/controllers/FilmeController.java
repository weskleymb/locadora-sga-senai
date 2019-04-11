package br.senai.rn.locadora.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.senai.rn.locadora.models.Categoria;
import br.senai.rn.locadora.models.Filme;
import br.senai.rn.locadora.services.CategoriaService;

@Controller
@RequestMapping("/filme")
public class FilmeController extends AbstractController<Filme> {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Override
	public String index(Model model) {
		List<Categoria> categorias = categoriaService.findAll();
		model.addAttribute("categoriaList", categorias);
		return super.index(model);
	}
	
	@Override
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		List<Categoria> categorias = categoriaService.findAll();
		model.addAttribute("categoriaList", categorias);
		return super.editar(id, model);
	}
	
}
