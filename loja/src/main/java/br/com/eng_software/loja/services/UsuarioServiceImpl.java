package br.com.eng_software.loja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eng_software.loja.dao.UsuarioDAO;
import br.com.eng_software.loja.model.Usuario;

@Component
public class UsuarioServiceImpl implements IUsuarioService {
	@Autowired
	private UsuarioDAO dao;

	@Override
	public Usuario recuperarUsuario(Usuario original) {
		return dao.findByUsernameOrEmail(original.getUsername(), original.getEmail());
	}
	
	
}
