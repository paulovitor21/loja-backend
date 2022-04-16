package br.com.eng_software.loja.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eng_software.loja.dao.PedidoDAO;
import br.com.eng_software.loja.model.ItemPedido;
import br.com.eng_software.loja.model.Pedido;

@Component
public class PedidoServiceImpl implements IPedidoService {
	@Autowired
	private PedidoDAO dao;

	@Override
	public Pedido inserirPedido(Pedido novo) {
		try {
			double total = 0.0;
			for (ItemPedido item: novo.getItensPedido()) {
				item.setPrecoUnitario(item.getProduto().getPreco());
				item.setPrecoTotal(item.getPrecoUnitario() * item.getQtdeItem());
				total += item.getPrecoTotal();
			}
			for (ItemPedido item: novo.getItensPedido()) {
				item.setPedido(novo);
			}
			novo.setStatus(Pedido.NOVO_PEDIDO);
			novo.setValorTotal(total);
			dao.save(novo);
			return novo;
			
		}catch (Exception ex) {
			return null;
		}
	}

	@Override
	public ArrayList<Pedido> buscarPorStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido mudarStatus(Pedido pedido, int novoStatus) {
		try {
			pedido.setStatus(novoStatus);
			dao.save(pedido);
			return pedido;
		}
		catch (Exception ex) {
			return null;
		}
	}

	@Override
	public ArrayList<Pedido> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
