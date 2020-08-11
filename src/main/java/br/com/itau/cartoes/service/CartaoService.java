package br.com.itau.cartoes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.cartoes.dto.CartaoDTO;
import br.com.itau.cartoes.exception.CartaoException;
import br.com.itau.cartoes.exception.ClienteException;
import br.com.itau.cartoes.model.Cartao;
import br.com.itau.cartoes.model.Cliente;
import br.com.itau.cartoes.repository.CartaoRepository;
import br.com.itau.cartoes.repository.ClienteRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cartao criar(CartaoDTO cartaoDTO) {
		
		Optional<Cliente> cliente = clienteRepository.findById(cartaoDTO.getClienteId());
		
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

		Optional<Cliente> cliente = clienteRepository.findById(clientId);

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
