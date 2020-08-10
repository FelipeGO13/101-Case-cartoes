package br.com.itau.cartoes.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.cartoes.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

}
