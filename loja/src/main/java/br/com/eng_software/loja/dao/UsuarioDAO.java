package br.com.eng_software.loja.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.eng_software.loja.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	public Usuario findByUsernameOrEmail(String username, String email);
}
