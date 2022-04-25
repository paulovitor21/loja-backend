package br.com.eng_software.loja.services;

import java.util.ArrayList;

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
		
		Usuario user = dao.findByUsernameOrEmail(original.getUsername(), original.getEmail());
		if (user != null)
		if (user.getSenha().equals(original.getSenha()) && user.getAtivo() == 1) {
			user.setSenha(null);
			return user;
		}
		return null;
	}

	@Override
	public ArrayList<Usuario> recuperarTodos() {
		return (ArrayList<Usuario>) dao.findAll();
	}

	@Override
	public Usuario adicionarNovo(Usuario novo) {
		if (novo.getUsername().length() > 0 && novo.getNome().length() > 0 && novo.getEmail().length() > 0 && novo.getSenha().length() > 0) {
			novo.setAtivo(1);
			try {
				dao.save(novo);
				return novo;
			}
			catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public Usuario atualizarUsuario(Usuario user) {
		try {
			dao.save(user);
			return user;
		}
		catch (Exception ex) {
			return null;
		}
	}

	@Override
	public Usuario recuperarPeloId(int id) {
		return dao.findById(id).orElse(null);
	}
}
