package br.com.eng_software.loja.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Integer id;
	
	@Column(name = "nome_produto", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "detalhe_produto", length = 500)
	private String detalhe;
	
	@Column(name = "link_foto", length = 255, nullable = false)
	private String linkFoto;
	
	@Column(name = "preco_produto", nullable = false)
	private double preco;
	
	@Column(name = "preco_promocional", nullable = false)
	private double precoPromo;
	
	@Column(name = "disponivel")
	private int disponivel;
	
	@Column(name = "destaque")
	private int destaque;
	
	@Column(name = "pronta_entrega")
	private int prontaEntrega;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getLinkFoto() {
		return linkFoto;
	}

	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public double getPrecoPromo() {
		return precoPromo;
	}

	public void setPrecoPromo(double precoPromo) {
		this.precoPromo = precoPromo;
	}

	public int getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(int disponivel) {
		this.disponivel = disponivel;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getDestaque() {
		return destaque;
	}

	public void setDestaque(int destaque) {
		this.destaque = destaque;
	}

	public int getProntaEntrega() {
		return prontaEntrega;
	}

	public void setProntaEntrega(int prontaEntrega) {
		this.prontaEntrega = prontaEntrega;
	}
	
	
	
	
}
