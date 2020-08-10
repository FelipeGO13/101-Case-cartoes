package br.com.itau.cartoes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.cartoes.dto.PagamentoDTO;
import br.com.itau.cartoes.model.Pagamento;
import br.com.itau.cartoes.service.PagamentoService;

@RestController
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@PostMapping("/pagamento")
	public ResponseEntity<?> criar(@Valid @RequestBody PagamentoDTO pagamento) {
		return new ResponseEntity<>(pagamentoService.criar(pagamento), HttpStatus.CREATED);
	}
	
	@GetMapping("/pagamentos/{id_cartao}")
	public Iterable<Pagamento> consultarPagamentoPorCartao(@PathVariable(name="id_cartao") int cartaoId){
		return pagamentoService.consultaPorCartao(cartaoId);
	}

}
