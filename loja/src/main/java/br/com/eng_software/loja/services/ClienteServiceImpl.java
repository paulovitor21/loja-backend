package br.com.eng_software.loja.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eng_software.loja.dao.ClienteDAO;
import br.com.eng_software.loja.dto.CompradorDTO;
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

	@Override
	public ArrayList<Cliente> buscarPorLetra(String letra) {
		return dao.findByNomeStartsWith(letra);
	}

	@Override
	public ArrayList<Cliente> buscarPorPalavraChave(String palavraChave) {
		return dao.findByNomeContaining(palavraChave);
	}

	@Override
	public ArrayList<Cliente> buscarTodos() {
		return dao.findByOrderByNomeAsc();
	}

	@Override
	public ArrayList<CompradorDTO> buscarCompradores(int idProduto) {
		return dao.recuperarCompradores(idProduto);
	}

	@Override
	public ArrayList<Cliente> buscarAniversariantes(int mes) {
		return dao.recuperarAniversariantes(mes);
	}
	
}
