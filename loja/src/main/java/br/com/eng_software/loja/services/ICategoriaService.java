package br.com.eng_software.loja.services;

import java.util.ArrayList;

import br.com.eng_software.loja.model.Categoria;

public interface ICategoriaService {
	// Este método recebe uma categoria só com o nome preenchido e adiciona no banco
	public Categoria inserirNovaCategoria(Categoria categoria);
	
	// Este método vai alterar a categoria existente e retorná-la se o update deu certo e null caso contrário
	public Categoria alterarCategoria(Categoria categoria);
	
	// Este método vai recuperar todas as categorias sem filtro
	public ArrayList<Categoria> recuperarTodasCategorias();
	
	// Este método vai recuperar todas as categorias por palavra chave
	public ArrayList<Categoria> recuperarPorPalavraChave(String palavraChave);
	
	// Este método vai recuperar 1 unica categoria por id
	public Categoria recuperarPorId(int id);
	
	// recuperar todas ordenadas pelo Id
	public ArrayList<Categoria> recuperarTodasOrdenadasPeloId();
}
