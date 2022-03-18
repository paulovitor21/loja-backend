package br.com.eng_software.loja.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eng_software.loja.model.Categoria;
import br.com.eng_software.loja.services.ICategoriaService;

@RestController
public class CategoriaController {
	@Autowired
	private ICategoriaService service;
	
	// Consulta categoria
	@GetMapping("/categoria")
	public ResponseEntity<ArrayList<Categoria>> listarTodas() {
		return ResponseEntity.ok().body(service.recuperarTodasCategorias());
	}
	
	// Consulta categoria por palavrachave
	@GetMapping("/categoria/search")
	public ResponseEntity<ArrayList<Categoria>> recuperarPorPalavraChave(@RequestParam(name="key") String palavraChave) {
		if (palavraChave != null) {
			return ResponseEntity.ok().body(service.recuperarPorPalavraChave(palavraChave));
		}
		return ResponseEntity.badRequest().build();
	}
	
	// Envio -> Inserção de nova categoria
	@PostMapping("/categoria")
	public ResponseEntity<Categoria> adicionarNova(@RequestBody Categoria categoria) {
		if (categoria.getId() != null) {
			categoria.setId(null);
		}
		Categoria resultado = service.inserirNovaCategoria(categoria);
		if (resultado != null) {
			return ResponseEntity.status(201).body(resultado);
		}
		return ResponseEntity.badRequest().build();
	}
	
	// Alterar -> Categoria existente
	@PutMapping("/categoria")
	public ResponseEntity<Categoria> alterarDados(@RequestBody Categoria categoria) {
		Categoria resultado = service.alterarCategoria(categoria);
		if (resultado != null) {
			return ResponseEntity.ok(resultado);
		}
		return ResponseEntity.badRequest().build();
	}
}