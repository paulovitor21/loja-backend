package br.com.eng_software.loja.dto;

import java.time.LocalDate;

public class FiltroPedidoDTO {
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String nome;
	private int novo;
	private int pago;
	private int transporte;
	private int entregue;
	private int posVenda;
	private int finalizado;
	private int cancelado;
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNovo() {
		return novo;
	}
	public void setNovo(int novo) {
		this.novo = novo;
	}
	public int getPago() {
		return pago;
	}
	public void setPago(int pago) {
		this.pago = pago;
	}
	public int getTransporte() {
		return transporte;
	}
	public void setTransporte(int transporte) {
		this.transporte = transporte;
	}
	public int getEntregue() {
		return entregue;
	}
	public void setEntregue(int entregue) {
		this.entregue = entregue;
	}
	public int getPosVenda() {
		return posVenda;
	}
	public void setPosVenda(int posVenda) {
		this.posVenda = posVenda;
	}
	public int getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(int finalizado) {
		this.finalizado = finalizado;
	}
	public int getCancelado() {
		return cancelado;
	}
	public void setCancelado(int cancelado) {
		this.cancelado = cancelado;
	}
	
	
	public String toString() {
		return "FiltroPedidoDTO [dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", nome=" + nome + ", novo="
				+ novo + ", pago=" + pago + ", transporte=" + transporte + ", entregue=" + entregue + ", posVenda="
				+ posVenda + ", finalizado=" + finalizado + ", cancelado=" + cancelado + "]";
	}
	
}
