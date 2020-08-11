package br.com.itau.cartoes.cliente.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.cartoes.cliente.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

}
