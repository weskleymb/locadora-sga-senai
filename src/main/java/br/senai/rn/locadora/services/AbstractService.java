package br.senai.rn.locadora.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import br.senai.rn.locadora.models.AuditedEntity;
import br.senai.rn.locadora.repositories.GenericRepository;

public abstract class AbstractService<T extends AuditedEntity> {

	@Autowired
	private GenericRepository<T> repository;
	
	public void save(T entity) {
		repository.save(entity);
	}
	
	public void remove(T entity) {
		repository.delete(entity);
	}
	
	public void remove(Long id) {
		repository.deleteById(id);
	}
	
	public T find(Long id) {
		T entity = repository.findById(id).get();
		return entity;
	}
	
	public List<T> findAll() {
		List<T> entities = repository.findAll();
		return entities;
	}
	
}
