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

	protected final String PAGE_INDEX = this.getEntityName().concat("/index");
	protected final String REDIRECT_INDEX = "redirect:/".concat(this.getEntityName());
	protected final String URL_EDIT = "/editar/{id}";
	protected final String URL_DELETE = "/remover/{id}";
	
	@Autowired
	private AbstractService<T> service;
	
	@GetMapping
	public String index(Model model) {
		if (!model.containsAttribute(this.getEntityList()) || !model.containsAttribute(this.getEntityName())) {
			try {
				model.addAttribute(this.getEntityList(), this.service.findAll());
				model.addAttribute(this.getEntityName(), getGenericClass().getDeclaredConstructor().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return PAGE_INDEX;
	}
	
	@GetMapping(URL_EDIT)
	public String editar(@PathVariable Long id, Model model) {
		model.addAttribute(this.getEntityList(), this.service.findAll());
		model.addAttribute(this.getEntityName(), this.service.find(id));
		return PAGE_INDEX;
	}
	
	@GetMapping(URL_DELETE)
	public String remover(@PathVariable Long id, Model model) {
		service.remove(id);
		return REDIRECT_INDEX;
	}
	
	@PostMapping
	public String salvar(T entity) {
		service.save(entity);
		return REDIRECT_INDEX;
	}
	
	protected Class<T> getGenericClass() {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}
	
	protected String getEntityName() {
		return StringUtils.uncapitalize(getGenericClass().getSimpleName());
	}
	
	protected String getEntityList() {
		return getEntityName().concat("List");
	}
	
}
