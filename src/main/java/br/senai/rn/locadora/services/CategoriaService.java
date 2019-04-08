package br.senai.rn.locadora.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.rn.locadora.models.Categoria;
import br.senai.rn.locadora.repositories.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public void salvar(Categoria categoria) {
		repository.save(categoria);
	}
	
	public void remover(Categoria categoria) {
		repository.delete(categoria);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public Categoria obter(Long id) {
		return repository.findById(id).get();
	}
	
	public List<Categoria> obterTodos() {
		return repository.findAll();
	}
	
}
