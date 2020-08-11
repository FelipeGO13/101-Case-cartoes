package br.com.itau.cartoes.cartao.repository;

import java.util.Optional;

import br.com.itau.cartoes.cliente.model.Cliente;
import org.springframework.data.repository.CrudRepository;

import br.com.itau.cartoes.cartao.model.Cartao;

public interface CartaoRepository extends CrudRepository<Cartao, Integer>{
	Optional<Cartao> findByNumero(String numero);
	Iterable<Cartao> findByCliente(Cliente cliente);
}
