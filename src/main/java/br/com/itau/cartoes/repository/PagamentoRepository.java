package br.com.itau.cartoes.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.cartoes.model.Cartao;
import br.com.itau.cartoes.model.Pagamento;

public interface PagamentoRepository extends CrudRepository<Pagamento, Integer>{
	Iterable<Pagamento> findByCartao(Cartao cartao);
}
