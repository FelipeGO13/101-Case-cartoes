package br.com.itau.cartoes.pagamento.service;

import java.util.Optional;

import br.com.itau.cartoes.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.cartoes.pagamento.dto.PagamentoDTO;
import br.com.itau.cartoes.cartao.exception.CartaoException;
import br.com.itau.cartoes.cartao.model.Cartao;
import br.com.itau.cartoes.pagamento.model.Pagamento;
import br.com.itau.cartoes.cartao.repository.CartaoRepository;
import br.com.itau.cartoes.pagamento.repository.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Pagamento criar(PagamentoDTO pagamentoDTO) {
		
		Optional<Cartao> cartao = cartaoRepository.findById(pagamentoDTO.getCartaoId());
		
		if(!cartao.isPresent()) {
			throw new CartaoException("Cartão", "Cartão não encontrado");
		}
		
		Pagamento pagamento = new Pagamento();
		
		pagamento.setCartao(cartao.get());
		pagamento.setDescricao(pagamentoDTO.getDescricao());
		pagamento.setValor(pagamentoDTO.getValor());
		
		return pagamentoRepository.save(pagamento);
	}
	
	public Iterable<Pagamento> consultaPorCartao(int cartaoId){
		Optional<Cartao> cartao = cartaoRepository.findById(cartaoId);
		
		if(!cartao.isPresent()) {
			throw new CartaoException("Cartão", "Cartão não encontrado");
		}
		
		return pagamentoRepository.findByCartao(cartao.get());
		
	}
	
}
