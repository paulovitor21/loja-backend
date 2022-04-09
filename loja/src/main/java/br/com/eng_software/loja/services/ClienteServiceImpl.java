package br.com.eng_software.loja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eng_software.loja.dao.ClienteDAO;
import br.com.eng_software.loja.model.Cliente;

@Component
public class ClienteServiceImpl implements IClienteService {
	@Autowired
	private ClienteDAO dao;

	@Override
	public Cliente buscarPeloCpf(String cpf) {
		
		return dao.findByCpf(cpf);
	}

	@Override
	public Cliente atualizarDados(Cliente dadosOriginais) {
		
		return dao.save(dadosOriginais);
	}
	
}
