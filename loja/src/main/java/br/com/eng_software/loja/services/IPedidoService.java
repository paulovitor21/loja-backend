package br.com.eng_software.loja.services;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.eng_software.loja.model.Pedido;

public interface IPedidoService {
	public Pedido inserirPedido(Pedido novo);
	public ArrayList<Pedido> buscarPorStatus(int status);
	public Pedido mudarStatus(Pedido pedido, int novoStatus );
	public ArrayList<Pedido> buscarPorPeriodo(LocalDate inicio, LocalDate fim);
	
}
