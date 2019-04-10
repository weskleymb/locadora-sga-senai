package br.senai.rn.locadora.controllers;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.senai.rn.locadora.models.AuditedEntity;
import br.senai.rn.locadora.services.AbstractService;

public abstract class AbstractController<T extends AuditedEntity> {

	@Autowired
	private AbstractService<T> service;
	
	@GetMapping
	public String index(Model model) {
		if (!model.containsAttribute(this.getEntityList()) || !model.containsAttribute(this.getEntityName())) {
			try {
				model.addAttribute(this.getEntityList(), this.service.obterTodos());
				model.addAttribute(this.getEntityName(), getClassType().getDeclaredConstructor().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return this.getEntityName().concat("/index");
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		model.addAttribute(this.getEntityList(), this.service.obterTodos());
		model.addAttribute(this.getEntityName(), this.service.obter(id));
		return this.getEntityName().concat("/index");
	}
	
	@GetMapping("/remover/{id}")
	public String remover(@PathVariable Long id, Model model) {
		service.remover(id);
		return "redirect:/".concat(this.getEntityName());
	}
	
	@PostMapping
	public String salvar(T entity) {
		service.salvar(entity);
		return "redirect:/".concat(this.getEntityName());
	}
	
	protected Class<T> getClassType() {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}
	
	protected String getEntityName() {
		return StringUtils.uncapitalize(getClassType().getSimpleName());
	}
	
	protected String getEntityList() {
		return getEntityName().concat("Lista");
	}
	
}
