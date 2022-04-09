package br.com.eng_software.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.eng_software.loja.model.Cliente;
import br.com.eng_software.loja.services.IClienteService;

@RestController
@CrossOrigin("*")
public class ClienteController {
	
	@Autowired
	private IClienteService service;
	
	@GetMapping("/cliente/{cpf}")
	public ResponseEntity<Cliente> buscarPeloCpf(@PathVariable String cpf) {
		Cliente resultado = service.buscarPeloCpf(cpf);
		if (resultado != null) {
			return ResponseEntity.ok(resultado);
		}
		return ResponseEntity.notFound().build();
	}
}
