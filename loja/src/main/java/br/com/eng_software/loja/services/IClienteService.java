package br.com.eng_software.loja.services;

import br.com.eng_software.loja.model.Cliente;

public interface IClienteService {
	public Cliente buscarPeloTelefone(String telefone);
	public Cliente atualizarDados(Cliente dadosOriginais);
}
