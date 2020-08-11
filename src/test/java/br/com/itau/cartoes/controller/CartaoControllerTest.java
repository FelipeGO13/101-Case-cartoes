package br.com.itau.cartoes.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import br.com.itau.cartoes.cartao.controller.CartaoController;
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

import br.com.itau.cartoes.cartao.dto.CartaoDTO;
import br.com.itau.cartoes.cartao.mapper.CartaoMapper;
import br.com.itau.cartoes.cartao.model.Cartao;
import br.com.itau.cartoes.cliente.model.Cliente;
import br.com.itau.cartoes.cartao.service.CartaoService;
import br.com.itau.cartoes.cliente.service.ClienteService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CartaoController.class)
public class CartaoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CartaoService cartaoService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private CartaoMapper cartaoMapper;
	
	private Cliente cliente;
	
	private Cartao cartao;
	
	private CartaoDTO cartaoDTO;
	
	private CartaoDTO cartaoAtivoDTO;
	
	private int clienteId = 1;
	
	private int cartaoId = 1;
	
	private String numero = "1";
	
	private ObjectMapper mapper = new ObjectMapper();
	
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
		
		cartaoAtivoDTO = new CartaoDTO();
		cartaoAtivoDTO.setAtivo(true);
	}
	
	
	@Test
	public void consultarPorNumeroTest() throws Exception {
		when(cartaoService.consultaPorNumero(Mockito.anyString())).thenReturn(Optional.of(cartao));
		when(cartaoMapper.mapearCartaoparaCartaoDTOSemAtivo(Mockito.any(Cartao.class))).thenReturn(cartaoDTO);
	
		mockMvc.perform(get("/cartao/"+numero))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.numero").value(numero))
				.andExpect(jsonPath("$.clienteId").value(clienteId));
	}
	
	@Test
	public void criarTest() throws Exception {
		String cartaoJson = mapper.writeValueAsString(cartaoDTO);
		
		when(cartaoService.criar(Mockito.any(CartaoDTO.class))).thenReturn(cartao);
		when(cartaoMapper.mapearCartaoparaCartaoDTO(Mockito.any(Cartao.class))).thenReturn(cartaoDTO);
		
		mockMvc.perform(post("/cartao")
			.contentType(MediaType.APPLICATION_JSON)
			.content(cartaoJson))
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.numero").value(numero))
			.andExpect(jsonPath("$.clienteId").value(clienteId))
			.andExpect(jsonPath("$.ativo").value(Boolean.FALSE));
		
	}
	
	@Test
	public void ativarDesativarTest() throws Exception {
		String cartaoJson = mapper.writeValueAsString(cartaoAtivoDTO);
		cartaoDTO.setAtivo(Boolean.TRUE);
		
		when(cartaoService.editar(Mockito.anyString(), Mockito.any(Cartao.class))).thenReturn(cartao);
		when(cartaoMapper.mapearCartaoparaCartaoDTO(Mockito.any(Cartao.class))).thenReturn(cartaoDTO);
		
		mockMvc.perform(patch("/cartao/"+numero)
			.contentType(MediaType.APPLICATION_JSON)
			.content(cartaoJson))
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.numero").value(numero))
			.andExpect(jsonPath("$.clienteId").value(clienteId))
			.andExpect(jsonPath("$.ativo").value(Boolean.TRUE));
		
	}
	
}
