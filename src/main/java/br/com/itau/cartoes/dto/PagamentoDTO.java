package br.com.itau.cartoes.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PagamentoDTO {
	
	private int id;
	
	@JsonProperty("cartao_id")
	private int cartaoId;

	private String descricao;

	private BigDecimal valor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCartaoId() {
		return cartaoId;
	}

	public void setCartaoId(int cartaoId) {
		this.cartaoId = cartaoId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
