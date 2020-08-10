package br.com.itau.cartoes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.cartoes.exception.ClienteException;
import br.com.itau.cartoes.model.Cliente;
import br.com.itau.cartoes.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente criar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Optional<Cliente> buscar(int id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (!cliente.isPresent()) {
			throw new ClienteException("Cliente", "Cliente não encontrado");
		}

		return cliente;
	}

}
