package br.com.itau.cartoes.repository;

import br.com.itau.cartoes.model.Cliente;
import org.springframework.data.repository.CrudRepository;

import br.com.itau.cartoes.model.Cartao;
import br.com.itau.cartoes.model.Pagamento;

import javax.transaction.Transactional;

public interface PagamentoRepository extends CrudRepository<Pagamento, Integer>{
	Iterable<Pagamento> findByCartao(Cartao cartao);

	@Transactional
	Iterable<Pagamento> deleteByCartao(Cartao cartao);
}
