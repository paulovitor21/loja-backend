package br.com.eng_software.loja.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.eng_software.loja.dto.CompradorDTO;
import br.com.eng_software.loja.model.Cliente;

public interface ClienteDAO extends CrudRepository<Cliente, Integer> {
	public Cliente findByEmailOrTelefone(String email, String telefone);
	public Cliente findByTelefone(String telefone);
	public Cliente findByCpf(String cpf);
	public ArrayList<Cliente> findByNomeStartsWith(String prefixo);
	public ArrayList<Cliente> findByNomeContaining(String keyword);
	public ArrayList<Cliente> findByOrderByNomeAsc();
	
	/*
	 * select distinct cli.nome_cliente, cli.email_cliente, cli.telefone_cliente
		from tbl_cliente as cli inner join tbl_pedido on tbl_pedido.id_cliente = cli.id_cliente
		inner join tbl_itempedido on tbl_itempedido.id_pedido = tbl_pedido.id_pedido
		inner join tbl_produto on tbl_itempedido.id_produto = tbl_produto.id_produto
		where tbl_produto.id_produto = 7;
	 */
	
	@Query("SELECT DISTINCT new br.com.eng_software.loja.dto.CompradorDTO(cli.nome, cli.email, cli.telefone) "
			+ " from Cliente cli INNER JOIN Pedido ped ON cli.id = ped.cliente.id "
			+ " INNER JOIN "
			+ " ItemPedido itm ON itm.pedido.id = ped.id "
			+ " INNER JOIN "
			+ " Produto pro ON itm.produto.id = pro.id "
			+ " WHERE pro.id = :id ")
	
	public ArrayList<CompradorDTO> recuperarCompradores(@Param("id") int idProduto);
	
	@Query("SELECT new br.com.eng_software.loja.model.Cliente(cli.nome, cli.dataNasc, cli.telefone)"
			+ " from Cliente cli WHERE month(cli.dataNasc) = :mes")
	public ArrayList<Cliente> recuperarAniversariantes(@Param("mes") int mes);
}