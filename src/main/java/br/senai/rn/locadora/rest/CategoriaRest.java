package br.senai.rn.locadora.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.rn.locadora.models.Categoria;
import br.senai.rn.locadora.services.CategoriaService;

@RestController
@RequestMapping("/ws/categoria")
public class CategoriaRest {

	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public List<Categoria> buscarTodos() {
		return service.buscarTodos();
	}
	
}
