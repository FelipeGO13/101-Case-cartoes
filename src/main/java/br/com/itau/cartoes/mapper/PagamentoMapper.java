package br.com.itau.cartoes.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.itau.cartoes.dto.PagamentoDTO;
import br.com.itau.cartoes.model.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

	public PagamentoDTO mapearPagamentoParaPagamentoDTO(Pagamento pagamento) {
		PagamentoDTO pagamentoDTO = new PagamentoDTO();

		pagamentoDTO.setId(pagamento.getId());
		pagamentoDTO.setCartaoId(pagamento.getCartao().getId());
		pagamentoDTO.setDescricao(pagamento.getDescricao());
		pagamentoDTO.setValor(pagamento.getValor());
		
		return pagamentoDTO;
	}
	
	public Iterable<PagamentoDTO> mapearListaPagamentoParaPagamentoDTO(Iterable<Pagamento> pagamento) {
		List<PagamentoDTO> listaPagamentos = new ArrayList<>();
		for(Pagamento p : pagamento) {
			listaPagamentos.add(mapearPagamentoParaPagamentoDTO(p));
		}

		return listaPagamentos;
	}
}
