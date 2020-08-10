package br.com.itau.cartoes.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.itau.cartoes.model.Cliente;
import br.com.itau.cartoes.service.ClienteService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {
	

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClienteService clienteService;
	
	private Cliente cliente;
	
	private int clienteId = 1;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setId(clienteId);
		cliente.setNome("teste");
	}
	
	
	@Test
	public void consultarTest() throws Exception {
		when(clienteService.buscar(Mockito.anyInt())).thenReturn(Optional.of(cliente));
		mockMvc.perform(get("/cliente/"+clienteId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.nome").value("teste"));
	}
	
	@Test
	public void criarTest() throws Exception {
		String clienteJson = mapper.writeValueAsString(cliente);
		
		when(clienteService.criar(Mockito.any(Cliente.class))).thenReturn(cliente);
		mockMvc.perform(post("/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.content(clienteJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.nome").value("teste"));
	}

}
