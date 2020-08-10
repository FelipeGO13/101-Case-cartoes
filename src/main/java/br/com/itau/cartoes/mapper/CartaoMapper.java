package br.com.itau.cartoes.mapper;

import br.com.itau.cartoes.dto.CartaoDTO;
import br.com.itau.cartoes.model.Cartao;

public class CartaoMapper {

	public CartaoDTO mapearCartaoparaCartaoDTO(Cartao cartao) {

		CartaoDTO cartaoDTO = mapearCartaoparaCartaoDTOSemAtivo(cartao);
		cartaoDTO.setAtivo(cartao.isAtivo());

		return cartaoDTO;

	}

	public CartaoDTO mapearCartaoparaCartaoDTOSemAtivo(Cartao cartao) {

		CartaoDTO cartaoDTO = new CartaoDTO();

		cartaoDTO.setId(cartao.getId());
		cartaoDTO.setNumero(cartao.getNumero());
		cartaoDTO.setClienteId(cartao.getCliente().getId());

		return cartaoDTO;

	}

}
