package br.com.itau.cartoes.cartao.service;

import java.util.Optional;

import br.com.itau.cartoes.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.cartoes.cartao.dto.CartaoDTO;
import br.com.itau.cartoes.cartao.exception.CartaoException;
import br.com.itau.cartoes.cliente.exception.ClienteException;
import br.com.itau.cartoes.cartao.model.Cartao;
import br.com.itau.cartoes.cliente.model.Cliente;
import br.com.itau.cartoes.cartao.repository.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public Cartao criar(CartaoDTO cartaoDTO) {
		
		Optional<Cliente> cliente = clienteService.buscar(cartaoDTO.getClienteId());
		
		if(!cliente.isPresent()) {
			throw new ClienteException("Cliente", "Cliente não encontrado");
		}
		
		Cartao cartao = new Cartao();
		
		cartao.setCliente(cliente.get());
		cartao.setNumero(cartaoDTO.getNumero());
		
		return cartaoRepository.save(cartao);
	}
	
	public Optional<Cartao> consultaPorNumero(String numero){
		
		Optional<Cartao> cartao = cartaoRepository.findByNumero(numero);
		
		if(!cartao.isPresent()) {
			throw new CartaoException("Cartão", "Cartão não encontrado");
		}
		
		return cartao;
	}

	public Iterable<Cartao> consultaPorCliente(int clientId){

		Optional<Cliente> cliente = clienteService.buscar(clientId);

		if(!cliente.isPresent()) {
			throw new ClienteException("Cliente", "Cliente não encontrado");
		}

		return cartaoRepository.findByCliente(cliente.get());
	}

	public Cartao editar(String numero, Cartao cartao) {
		
		Optional<Cartao> cartaoSelecionado = cartaoRepository.findByNumero(numero);
		
		if(!cartaoSelecionado.isPresent()) {
			throw new CartaoException("Cartão", "Cartão não encontrado");
		}
		
		cartaoSelecionado.get().setAtivo(cartao.isAtivo());
		
		return cartaoRepository.save(cartaoSelecionado.get());
	}


}
