package br.com.eng_software.loja.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.eng_software.loja.model.Categoria;

public interface CategoriaDAO extends CrudRepository<Categoria, Integer> {
	// Consultas customizadas
	
	// 1 - Categoria por palavra chave
	public ArrayList<Categoria> findByNomeContaining(String palavra);
}
