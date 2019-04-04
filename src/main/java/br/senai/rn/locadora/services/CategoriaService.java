package br.senai.rn.locadora.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.rn.locadora.models.Categoria;
import br.senai.rn.locadora.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public void salvar(Categoria categoria) {
		repository.save(categoria);
	}
	
	public void remover(Categoria categoria) {
		repository.delete(categoria);
	}
	
	public void removerPorId(Long id) {
		repository.deleteById(id);
	}
	
	public Categoria buscarPorId(Long id) {
		return repository.findById(id).get();
	}
	
	public List<Categoria> buscarTodos() {
		return repository.findAll();
	}
	
}
