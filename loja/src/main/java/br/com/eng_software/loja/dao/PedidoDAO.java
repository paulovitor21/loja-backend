package br.com.eng_software.loja.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.eng_software.loja.dto.VendasPorDataDTO;
import br.com.eng_software.loja.model.Cliente;
import br.com.eng_software.loja.model.Pedido;

/* Possíveis combinações /*
 * 0 - todos
 * 1 - somente status
 * 2 - somente nome
 * 3 - nome e status
 * 4 - somente data
 * 5 - data e status
 * 6 - data e nome
 * 7 - data, nome e status
 */
public interface PedidoDAO extends CrudRepository<Pedido, Integer>{
	
	public ArrayList<Pedido> findAllByCliente(Cliente cliente);
	
	public ArrayList<Pedido> findAllByOrderByDataPedidoDesc();
	/* combinacao 1 */
	public ArrayList<Pedido> findAllByStatusInOrderByIdPedidoDesc(Collection<Integer> status);
	/* combinacao 2 */
	public ArrayList<Pedido> findAllByClienteInOrderByIdPedidoDesc(Collection<Cliente> cliente);
	/* combinacao 3 */
	public ArrayList<Pedido> findAllByClienteInAndStatusInOrderByIdPedidoDesc(Collection<Cliente> cliente, Collection<Integer> status);
	/* combinacao 4 */
	public ArrayList<Pedido> findAllByDataPedidoBetweenOrderByIdPedidoDesc(LocalDate inicio, LocalDate fim);
	/* combinacao 5 */
	public ArrayList<Pedido> findAllByDataPedidoBetweenAndStatusInOrderByIdPedidoDesc(LocalDate inicio, LocalDate fim, Collection<Integer> status);
	/* combinacao 6 */
	public ArrayList<Pedido> findAllByDataPedidoBetweenAndClienteInOrderByIdPedidoDesc(LocalDate inicio, LocalDate fim, Collection<Cliente> cliente);	
	/* combinacao 7 */
	public ArrayList<Pedido> findAllByDataPedidoBetweenAndClienteInAndStatusInOrderByIdPedidoDesc(LocalDate inicio, LocalDate fim, Collection<Cliente> cliente, Collection<Integer> status);
	
	public ArrayList<Pedido> findAllByStatusOrderByDataPedidoDesc(int status);
	
	@Query("SELECT new br.com.eng_software.loja.dto.VendasPorDataDTO(sum(valorTotal), dataPedido) "
			+ " FROM Pedido ped WHERE ped.dataPedido BETWEEN :dataIni AND :dataFim"
			+ " GROUP BY ped.dataPedido "
			+ " ORDER BY ped.dataPedido ASC")
	public ArrayList<VendasPorDataDTO> recuperarVendasPorData(@Param("dataIni") LocalDate dataIni,
			                                                  @Param("dataFim") LocalDate dataFim);
	
}
