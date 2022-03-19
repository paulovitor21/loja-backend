package br.com.eng_software.loja.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.eng_software.loja.model.Categoria;
import br.com.eng_software.loja.model.Produto;

public interface ProdutoDAO extends CrudRepository<Produto, Integer> {
	
	public ArrayList<Produto> findAllByDisponivel(int disponivel);
	public ArrayList<Produto> findAllByDisponivelAndCategoria(int disponivel, Categoria cat);
	public ArrayList<Produto> findAllByCategoria(Categoria categoria);
}
