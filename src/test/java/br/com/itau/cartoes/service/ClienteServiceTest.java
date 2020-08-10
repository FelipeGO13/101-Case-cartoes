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

import br.com.itau.cartoes.model.Cliente;
import br.com.itau.cartoes.repository.ClienteRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ClienteService.class)
public class ClienteServiceTest {

	@Autowired
	private ClienteService clienteService;

	@MockBean
	private ClienteRepository clienteRepository;
	
	private Cliente cliente;
	
	private int clienteId = 1;

	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setId(clienteId);
		cliente.setNome("teste");
	}
	
	@Test
	public void criarTest() {
		when(clienteRepository.save(Mockito.any(Cliente.class))).then(answer -> answer.getArgument(0));

		Cliente clienteCriado = clienteService.criar(cliente);

		assertEquals(cliente, clienteCriado);
	}
	
	@Test
	public void consultarPorIdTest() {
		when(clienteRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(cliente));

		Optional<Cliente> clienteEncontrado = clienteService.buscar(clienteId);

		assertEquals(cliente, clienteEncontrado.get());
	}
	
	@Test(expected = RuntimeException.class)
	public void consultarPorIdErroTest() {
		clienteService.buscar(10);
	}
	
}
