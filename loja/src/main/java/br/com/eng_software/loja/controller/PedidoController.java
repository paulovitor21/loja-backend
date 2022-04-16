package br.com.eng_software.loja.controller;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eng_software.loja.model.Cliente;
import br.com.eng_software.loja.model.Pedido;
import br.com.eng_software.loja.services.IClienteService;
import br.com.eng_software.loja.services.IPedidoService;

@RestController
@CrossOrigin("*")
public class PedidoController {
	
	@Autowired
	private IPedidoService service;
	
	@Autowired
	private IClienteService cliService;
	
	@PostMapping("/pedido")
	public ResponseEntity<Pedido> inserirNovoPedido(@RequestBody Pedido novo) {
		novo.setDataPedido(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		System.out.println("--- PEDIDO");
		System.out.println(novo.getObservacoes());
		System.out.println(novo.getDataPedido());
		System.out.println(novo.getItensPedido());
		System.out.println(novo.getCliente().getIdCliente());
		
		System.out.println("-----------------------");
		
		// Antes de gravar o pedido, preciso incluir o cliente na base
		Cliente cli = cliService.atualizarDados(novo.getCliente());
		
		novo.setCliente(cli); 
		
		novo = service.inserirPedido(novo);
		if (novo != null) {
			return ResponseEntity.status(201).body(novo);
		}
		else {
			return ResponseEntity.status(400).build();
		}
	}
	
	@GetMapping("/pedido")
	public ResponseEntity<ArrayList<Pedido>> recuperarTodos() {
		return ResponseEntity.ok(service.buscarTodos());
	}
	
	@PutMapping("/pedido/{id}")
	public ResponseEntity<Pedido> alterarStatus(@PathVariable int id, @RequestParam(name="status") int status) {
		try {
			Pedido pedido = service.mudarStatus(id, status);
			if (pedido != null) {
				return ResponseEntity.ok(pedido);
			}
			else {
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception ex) {
			return ResponseEntity.status(500).build();
		}
	}
	
	@GetMapping("/pedido/search/{id}")
	public ResponseEntity<Pedido> recuperarPeloId(@PathVariable int id) {
		return ResponseEntity.ok(service.buscarPeloId(id));
	}
}
