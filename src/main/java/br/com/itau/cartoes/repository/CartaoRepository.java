package br.com.itau.cartoes.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.cartoes.model.Cartao;

public interface CartaoRepository extends CrudRepository<Cartao, Integer>{
	Optional<Cartao> findByNumero(String numero);
}
