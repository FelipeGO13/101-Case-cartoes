package br.com.itau.cartoes.cartao.mapper;

import br.com.itau.cartoes.cartao.dto.CartaoDTO;
import br.com.itau.cartoes.cartao.model.Cartao;
import org.springframework.stereotype.Component;

@Component
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
