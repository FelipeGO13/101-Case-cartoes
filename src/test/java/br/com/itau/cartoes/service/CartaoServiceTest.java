package br.com.itau.cartoes.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.itau.cartoes.dto.CartaoDTO;
import br.com.itau.cartoes.model.Cartao;
import br.com.itau.cartoes.model.Cliente;
import br.com.itau.cartoes.repository.CartaoRepository;
import br.com.itau.cartoes.repository.ClienteRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CartaoService.class)
public class CartaoServiceTest {

	@Autowired
	private CartaoService cartaoService;

	@MockBean
	private CartaoRepository cartaoRepository;
	
	@MockBean
	private ClienteRepository clienteRepository;
	
	private Cartao cartao;
	
	private Cliente cliente;
	
	private CartaoDTO cartaoDTO;
	
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
		
		cartaoDTO = new CartaoDTO();
		cartaoDTO.setId(cartaoId);
		cartaoDTO.setClienteId(clienteId);
		cartaoDTO.setNumero(numero);
		cartaoDTO.setAtivo(false);
	}
	
	@Test
	public void criarTest() {
		when(cartaoRepository.save(Mockito.any(Cartao.class))).thenReturn(cartao);
		when(clienteRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(cliente));
		
		Cartao cartaoCriado = cartaoService.criar(cartaoDTO);

		assertEquals(cartao, cartaoCriado);
	}
	
	@Test(expected = RuntimeException.class)
	public void criarErroTest() {
		cartaoService.criar(cartaoDTO);
	}
	
	@Test
	public void consultaPorNumeroTest() {
		when(cartaoRepository.findByNumero(Mockito.anyString())).thenReturn(Optional.of(cartao));

		Optional<Cartao> cartaoEncontrado = cartaoService.consultaPorNumero(numero);

		assertEquals(cartao, cartaoEncontrado.get());
	}
	
	@Test(expected = RuntimeException.class)
	public void consultaPorNumeroErroTest() {
		cartaoService.consultaPorNumero("10");
	}
	
	@Test
	public void editarTest() {
		
		when(cartaoRepository.save(Mockito.any(Cartao.class))).thenReturn(cartao);
		when(cartaoRepository.findByNumero(Mockito.anyString())).thenReturn(Optional.of(cartao));

		Cartao cartaoEditado = cartaoService.editar(numero, cartao);
		assertEquals(cartao, cartaoEditado);
	}
	
	@Test(expected = RuntimeException.class)
	public void editarErroTest() {
		cartaoService.editar("10", cartao);
	}
	
	
}
