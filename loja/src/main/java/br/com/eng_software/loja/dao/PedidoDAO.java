package br.com.eng_software.loja.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.eng_software.loja.model.Cliente;
import br.com.eng_software.loja.model.Pedido;

public interface PedidoDAO extends CrudRepository<Pedido, Integer>{
	
	public ArrayList<Pedido> findAllByCliente(Cliente cliente);
	public ArrayList<Pedido> findAllByDataPedidoBetween(LocalDate inicio, LocalDate fim);
	public ArrayList<Pedido> findAllByOrderByDataPedidoDesc();
	
	
	public ArrayList<Pedido> findAllByStatusOrderByDataPedidoDesc(int status);
	
}
