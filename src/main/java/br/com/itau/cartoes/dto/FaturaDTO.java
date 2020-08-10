package br.com.itau.cartoes.dto;

public class FaturaDTO {

	private Iterable<PagamentoDTO> pagamentos;

	public Iterable<PagamentoDTO> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(Iterable<PagamentoDTO> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
}
