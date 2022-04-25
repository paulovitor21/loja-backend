package br.com.eng_software.loja.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eng_software.loja.dao.ClienteDAO;
import br.com.eng_software.loja.dao.PedidoDAO;
import br.com.eng_software.loja.dto.FiltroPedidoDTO;
import br.com.eng_software.loja.dto.VendasPorDataDTO;
import br.com.eng_software.loja.model.Cliente;
import br.com.eng_software.loja.model.ItemPedido;
import br.com.eng_software.loja.model.Pedido;

@Component
public class PedidoServiceImpl implements IPedidoService {
	@Autowired
	private PedidoDAO dao;
	
	@Autowired
	private ClienteDAO cliDao;

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
		return dao.findAllByStatusOrderByDataPedidoDesc(status);
	}

	@Override
	public Pedido mudarStatus(int idPedido, int novoStatus) {
		try {
			Pedido pedido = dao.findById(idPedido).get();
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
		return dao.findAllByDataPedidoBetweenOrderByIdPedidoDesc(inicio, fim);
	}

	@Override
	public Pedido buscarPeloId(int id) {
		return dao.findById(id).get();
	}

	@Override
	public ArrayList<Pedido> buscarTodos() {
		return dao.findAllByOrderByDataPedidoDesc();
	}

	@Override
	public List<VendasPorDataDTO> recuperarTotaisUltimaSemana(LocalDate inicio, LocalDate fim) {
		// TODO Auto-generated method stub
		ArrayList<VendasPorDataDTO> lista = dao.recuperarVendasPorData(inicio, fim);
		
		return lista;
	}

	@Override
	public ArrayList<Pedido> filtrarPorVariosCriterios(FiltroPedidoDTO filtro) {
		// precisamos esgotar as combinações
				boolean temNome = filtro.getNome() != null && !filtro.getNome().trim().isEmpty();
				boolean temData = filtro.getDataInicio() != null && filtro.getDataFim() != null;
				boolean temStatus = filtro.getCancelado() != 0 || filtro.getEntregue() != 0 || filtro.getPago() != 0 || filtro.getNovo() != 0 || filtro.getTransporte() != 0 || filtro.getPosVenda() != 0 || filtro.getFinalizado() != 0;
				
				
				if (!temData && !temNome &&  !temStatus) {
					// condicao 0
					System.out.println("----> condicao 0");
					return dao.findAllByOrderByDataPedidoDesc();
					//return dao.findAllByStatusNotOrderByDataPedidoDesc();
							
				}
				else if (!temData && !temNome && temStatus) {
					// condicao 1
					System.out.println("----> condicao 1");
					return dao.findAllByStatusInOrderByIdPedidoDesc(this.getStatus(filtro));			
				}
				else if (!temData && temNome && !temStatus) {
					// condicao 2 - buscar clientes
					System.out.println("----> condicao 2");
					ArrayList<Cliente> clientes = cliDao.findByNomeContaining(filtro.getNome());
					return dao.findAllByClienteInOrderByIdPedidoDesc(clientes);
				}
				else if (!temData && temNome && temStatus) {
					// condicao 3	
					System.out.println("----> condicao 3");
					ArrayList<Cliente> clientes = cliDao.findByNomeContaining(filtro.getNome());
					return dao.findAllByClienteInAndStatusInOrderByIdPedidoDesc(clientes, this.getStatus(filtro));
				}
				else if (temData && !temNome && !temStatus) {
					// condicao 4;
					System.out.println("----> condicao 4");
					return dao.findAllByDataPedidoBetweenOrderByIdPedidoDesc(filtro.getDataInicio(), filtro.getDataFim());
				}
				else if (temData && !temNome && temStatus) {
					// condicao 5
					System.out.println("----> condicao 5");
					return dao.findAllByDataPedidoBetweenAndStatusInOrderByIdPedidoDesc(filtro.getDataInicio(), filtro.getDataFim(), this.getStatus(filtro));
				}
				else if (temData && temNome && !temStatus) {
					//condicao 6
					System.out.println("----> condicao 6");
					ArrayList<Cliente> clientes = cliDao.findByNomeContaining(filtro.getNome());
					return dao.findAllByDataPedidoBetweenAndClienteInOrderByIdPedidoDesc(filtro.getDataInicio(), filtro.getDataFim(), clientes);		
				}
				else if (temData && temNome && temStatus) {
					// condicao 7
					System.out.println("----> condicao 7");
					ArrayList<Cliente> clientes = cliDao.findByNomeContaining(filtro.getNome());
					return dao.findAllByDataPedidoBetweenAndClienteInAndStatusInOrderByIdPedidoDesc(filtro.getDataInicio(), filtro.getDataFim(), clientes, this.getStatus(filtro));
				}
				return null;
				
				
				
				
			}
			
			// funcoes utilitárias
			private Collection<Integer> getStatus(FiltroPedidoDTO filtro){
				Collection<Integer> status = new ArrayList<Integer>();
				if (filtro.getPago() != 0) status.add(Pedido.PAGO);
				if (filtro.getCancelado() != 0) status.add(Pedido.CANCELADO);
				if (filtro.getEntregue() != 0) status.add(Pedido.ENTREGUE);
				if (filtro.getNovo() != 0) status.add(Pedido.NOVO_PEDIDO);
				if (filtro.getTransporte() != 0) status.add(Pedido.EM_TRANSPORTE);
				if (filtro.getPosVenda() != 0 )status.add(Pedido.POS_VENDA);
				if (filtro.getFinalizado() != 0) status.add(Pedido.FINALIZADO);
				
				System.out.println(status);
				return status;
			}
			
	
}
