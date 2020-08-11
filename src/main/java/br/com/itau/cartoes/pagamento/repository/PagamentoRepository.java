package br.com.itau.cartoes.pagamento.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.cartoes.cartao.model.Cartao;
import br.com.itau.cartoes.pagamento.model.Pagamento;

import javax.transaction.Transactional;

public interface PagamentoRepository extends CrudRepository<Pagamento, Integer>{
	Iterable<Pagamento> findByCartao(Cartao cartao);

	@Transactional
	Iterable<Pagamento> deleteByCartao(Cartao cartao);
}
