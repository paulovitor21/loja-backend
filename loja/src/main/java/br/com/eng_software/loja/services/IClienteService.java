package br.com.eng_software.loja.services;

import java.util.ArrayList;

import br.com.eng_software.loja.dto.CompradorDTO;
import br.com.eng_software.loja.model.Cliente;

public interface IClienteService {
	public Cliente buscarPeloCpf(String cpf);
	public Cliente atualizarDados(Cliente dadosOriginais);
	public ArrayList<Cliente> buscarPorLetra(String letra);
	public ArrayList<Cliente> buscarPorPalavraChave(String palavraChave);
	public ArrayList<Cliente> buscarTodos();
	public ArrayList<Cliente> buscarAniversariantes(int mes);
	public ArrayList<CompradorDTO> buscarCompradores(int idProduto);
	
	
}
