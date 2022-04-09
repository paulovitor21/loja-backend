package br.com.eng_software.loja.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.eng_software.loja.model.Cliente;

public interface ClienteDAO extends CrudRepository<Cliente, Integer> {
	public Cliente findByEmailOrTelefone(String email, String telefone);
	public Cliente findByTelefone(String telefone);
	public Cliente findByCpf(String cpf);
}
