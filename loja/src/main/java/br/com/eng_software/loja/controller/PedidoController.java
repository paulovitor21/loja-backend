package br.com.eng_software.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.eng_software.loja.model.Pedido;
import br.com.eng_software.loja.services.IPedidoService;

@RestController
public class PedidoController {
	
	@Autowired
	private IPedidoService service;
	
	@PostMapping("/pedido")
	public ResponseEntity<Pedido> inserirNovoPedido(@RequestBody Pedido novo) {
		System.out.println("--- PEDIDO");
		System.out.println(novo.getObservacoes());
		System.out.println(novo.getDataPedido());
		System.out.println(novo.getItensPedido());
		System.out.println(novo.getCliente().getIdCliente());
		
		System.out.println("-----------------------");
		
		novo = service.inserirPedido(novo);
		if (novo != null) {
			return ResponseEntity.status(201).body(novo);
		}
		else {
			return ResponseEntity.status(400).build();
		}
	}
}
