package br.com.eng_software.loja.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.eng_software.loja.dto.FiltroPedidoDTO;
import br.com.eng_software.loja.dto.VendasPorDataDTO;
import br.com.eng_software.loja.model.Pedido;

public interface IPedidoService {
	
	public Pedido inserirPedido(Pedido novo);
	public ArrayList<Pedido> buscarPorStatus(int status);
	public Pedido mudarStatus(int idPedido, int novoStatus);
	public ArrayList<Pedido> buscarPorPeriodo(LocalDate inicio, LocalDate fim);
	public ArrayList<Pedido> buscarTodos();
	public Pedido buscarPeloId(int id);
	
	public ArrayList<Pedido> filtrarPorVariosCriterios(FiltroPedidoDTO filtro);
	
	public List<VendasPorDataDTO> recuperarTotaisUltimaSemana(LocalDate inicio, LocalDate fim);
	
}
