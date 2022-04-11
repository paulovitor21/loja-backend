package br.com.eng_software.loja.services;

import java.util.ArrayList;

import br.com.eng_software.loja.model.Categoria;
import br.com.eng_software.loja.model.Produto;

public interface IProdutoService {
	public Produto inserirNovoProduto(Produto produto);
	public Produto alterarProduto(Produto produto);
	public ArrayList<Produto> listarTodos();
	public ArrayList<Produto> listarDisponiveis();
	public ArrayList<Produto> listarIndisponiveis();
	public ArrayList<Produto> listarPorCategoria(Categoria categoria);
	public ArrayList<Produto> listarPorPalavraChave(String palavraChave);
	public Produto recuperarPorId(int id);
	
}
