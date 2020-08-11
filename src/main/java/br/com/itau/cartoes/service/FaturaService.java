package br.com.itau.cartoes.service;

import br.com.itau.cartoes.dto.ExpirarDTO;
import br.com.itau.cartoes.dto.PagamentoFaturaDTO;
import br.com.itau.cartoes.exception.CartaoException;
import br.com.itau.cartoes.exception.ClienteException;
import br.com.itau.cartoes.model.Cartao;
import br.com.itau.cartoes.model.Cliente;
import br.com.itau.cartoes.model.Fatura;
import br.com.itau.cartoes.model.Pagamento;
import br.com.itau.cartoes.repository.CartaoRepository;
import br.com.itau.cartoes.repository.ClienteRepository;
import br.com.itau.cartoes.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class FaturaService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private ClienteRepository clienteRepository;

    public Fatura consultaPorClienteECartao(int clienteId, int cartaoId){
        Iterable<Cartao> cartoes = cartaoService.consultaPorCliente(clienteId);

        Optional<Cartao> cartao = StreamSupport.stream(cartoes.spliterator(), false).filter(c -> c.getId() == cartaoId).findFirst();

        if(!cartao.isPresent()) {
            throw new CartaoException("Cartão", "Cartão não encontrado");
        }

        Iterable<Pagamento> pagamentos = pagamentoRepository.findByCartao(cartao.get());

        Fatura fatura = new Fatura();

        fatura.setPagamentos(pagamentos);

        return fatura;
    }

    public Fatura pagarFatura(int clienteId, int cartaoId, PagamentoFaturaDTO pagamentoFaturaDTO){
        Iterable<Cartao> cartoes = cartaoService.consultaPorCliente(clienteId);

        Optional<Cartao> cartao = StreamSupport.stream(cartoes.spliterator(), false).filter(c -> c.getId() == cartaoId).findFirst();

        if(!cartao.isPresent()) {
            throw new CartaoException("Cartão", "Cartão não encontrado");
        }

        Iterable<Pagamento> pagamentos = pagamentoRepository.deleteByCartao(cartao.get());

        Fatura fatura = new Fatura();

        fatura.setPagamentos(pagamentos);
        fatura.setValorPago(pagamentoFaturaDTO.getValorPago());
        fatura.setDataPagamento(pagamentoFaturaDTO.getDataPagamento());

        return fatura;
    }

    public ExpirarDTO expirar(int clienteId, int cartaoId, ExpirarDTO expirarDTO){
        Iterable<Cartao> cartoes = cartaoService.consultaPorCliente(clienteId);

        Optional<Cartao> cartao = StreamSupport.stream(cartoes.spliterator(), false).filter(c -> c.getId() == cartaoId).findFirst();

        if(!cartao.isPresent()) {
            throw new CartaoException("Cartão", "Cartão não encontrado");
        }

        cartao.get().setAtivo(false);

        cartaoService.editar(cartao.get().getNumero(), cartao.get());

        return expirarDTO;
    }


}
