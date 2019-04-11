package br.senai.rn.locadora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.senai.rn.locadora.models.Categoria;

@Controller
@RequestMapping("/categoria")
public class CategoriaController extends AbstractController<Categoria> {}
