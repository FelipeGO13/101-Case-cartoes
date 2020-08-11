package br.com.itau.cartoes.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.itau.cartoes.repository.ClienteRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.itau.cartoes.dto.PagamentoDTO;
import br.com.itau.cartoes.model.Cartao;
import br.com.itau.cartoes.model.Cliente;
import br.com.itau.cartoes.model.Pagamento;
import br.com.itau.cartoes.repository.CartaoRepository;
import br.com.itau.cartoes.repository.PagamentoRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PagamentoService.class)
public class PagamentoServiceTest {

	@Autowired
	private PagamentoService pagamentoService;
	
	@MockBean
	private PagamentoRepository pagamentoRepository;
	
	@MockBean
	private CartaoRepository cartaoRepository;

	@MockBean
	private ClienteRepository clienteRepository;

	private Pagamento pagamento;
	
	private PagamentoDTO pagamentoDTO;
	
	private List<Pagamento> listaPagamentos;
	
	private Cliente cliente;
	
	private Cartao cartao;
	
	private int pagamentoId = 1;
	
	private int clienteId = 1;
	
	private int cartaoId = 1;
	
	private String numero = "1";
	
	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setId(clienteId);
		cliente.setNome("teste");
		
		cartao = new Cartao();
		cartao.setId(cartaoId);
		cartao.setCliente(cliente);
		cartao.setNumero(numero);
		cartao.setAtivo(false);
		
		pagamento = new Pagamento();
		pagamento.setId(pagamentoId);
		pagamento.setCartao(cartao);
		pagamento.setDescricao("teste");
		
		pagamentoDTO = new PagamentoDTO();
		pagamentoDTO.setCartaoId(cartaoId);
		pagamentoDTO.setDescricao("teste");
		pagamentoDTO.setValor(BigDecimal.TEN);
		
		listaPagamentos = new ArrayList<>();
		
		listaPagamentos.add(pagamento);
		
	}
	
	@Test
	public void criarTest() {
		when(pagamentoRepository.save(Mockito.any(Pagamento.class))).thenReturn(pagamento);
		when(cartaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(cartao));
		
		Pagamento pagamentoCriado = pagamentoService.criar(pagamentoDTO);

		assertEquals(pagamento, pagamentoCriado);
	}
	
	@Test(expected = RuntimeException.class)
	public void criarErroTest() {
		pagamentoDTO.setCartaoId(10);
		pagamentoService.criar(pagamentoDTO);
	}
	
	@Test
	public void consultaPorCartaoTest(){
		when(pagamentoRepository.findByCartao(Mockito.any(Cartao.class))).thenReturn(listaPagamentos);
		when(cartaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(cartao));
		
		Iterable<Pagamento> pagamentosIterable = pagamentoService.consultaPorCartao(cartaoId);
		List<Pagamento> pagamentosList = Lists.newArrayList(pagamentosIterable);
		
		assertEquals(pagamento, pagamentosList.get(0));
		
	}
	
	@Test(expected = RuntimeException.class)
	public void consultaPorCartaoErroTest(){
		pagamentoDTO.setCartaoId(10);
		pagamentoService.consultaPorCartao(cartaoId);
		
	}
}
