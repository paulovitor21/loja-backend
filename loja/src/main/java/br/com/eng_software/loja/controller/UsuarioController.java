package br.com.eng_software.loja.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.eng_software.loja.model.Usuario;
import br.com.eng_software.loja.security.JWTToken;
import br.com.eng_software.loja.security.JWTTokenUtil;
import br.com.eng_software.loja.services.IUsuarioService;

@RestController
@CrossOrigin("*")
public class UsuarioController {
	@Autowired
	private IUsuarioService service;
	
	@PostMapping("/login")
	public ResponseEntity<JWTToken> fazerLogin(@RequestBody Usuario dadosLogin) {
		Usuario user = service.recuperarUsuario(dadosLogin);
		
		if (user != null) { // usuario existe, precisa agora conferir a senha
				//  Cria o token do usuario
				JWTToken jwtToken = new JWTToken();
				
				jwtToken.setToken(JWTTokenUtil.generateToken(user));
				
				return ResponseEntity.ok(jwtToken);
		}
		return ResponseEntity.status(403).build();
	}
	
	@GetMapping("/usuario")
	public ResponseEntity<ArrayList<Usuario>> recuperarTodos() {
		return ResponseEntity.ok(service.recuperarTodos());
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> adicionarNovo(@RequestBody Usuario usuario) {
		Usuario res = service.adicionarNovo(usuario);
		if (res != null) {
			return ResponseEntity.status(200).body(res);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<Usuario> alterarDados(@RequestBody Usuario usuario, @PathVariable int id) {
		usuario.setId(id);
		Usuario res = service.atualizarUsuario(usuario);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> recuperarPeloId(@PathVariable int id) {
		Usuario res = service.recuperarPeloId(id);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
