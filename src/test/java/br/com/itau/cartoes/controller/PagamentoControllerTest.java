package br.com.itau.cartoes.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.itau.cartoes.pagamento.controller.PagamentoController;
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

import br.com.itau.cartoes.pagamento.dto.PagamentoDTO;
import br.com.itau.cartoes.pagamento.mapper.PagamentoMapper;
import br.com.itau.cartoes.cartao.model.Cartao;
import br.com.itau.cartoes.cliente.model.Cliente;
import br.com.itau.cartoes.pagamento.model.Pagamento;
import br.com.itau.cartoes.pagamento.service.PagamentoService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PagamentoController.class)
public class PagamentoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PagamentoService pagamentoService;
	
	@MockBean
	private PagamentoMapper pagamentoMapper;
	
	private Pagamento pagamento;
	
	private PagamentoDTO pagamentoDTO;
	
	private Iterable<PagamentoDTO> listaPagamentoDTO;
	
	private Cliente cliente;
	
	private Cartao cartao;
	
	private int pagamentoId = 1;
	
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
		
		pagamento = new Pagamento();
		pagamento.setId(pagamentoId);
		pagamento.setCartao(cartao);
		pagamento.setDescricao("teste");
		
		pagamentoDTO = new PagamentoDTO();
		pagamentoDTO.setCartaoId(cartaoId);
		pagamentoDTO.setDescricao("teste");
		pagamentoDTO.setValor(BigDecimal.TEN);
		
		List<PagamentoDTO> listaPagamentos = new ArrayList<>();
		
		listaPagamentos.add(pagamentoDTO);
		
		listaPagamentoDTO = listaPagamentos;
	}
	
	@Test
	public void criarTest() throws Exception {
		String pagamentoJson = mapper.writeValueAsString(pagamentoDTO);
		
		pagamentoDTO.setId(pagamentoId);
		when(pagamentoService.criar(Mockito.any(PagamentoDTO.class))).thenReturn(pagamento);
		when(pagamentoMapper.mapearPagamentoParaPagamentoDTO(Mockito.any(Pagamento.class))).thenReturn(pagamentoDTO);
		mockMvc.perform(post("/pagamento")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pagamentoJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.descricao").value("teste"))
				.andExpect(jsonPath("$.valor").value(BigDecimal.TEN))
				.andExpect(jsonPath("$.cartao_id").value(cartaoId));
				
	}
	
	@Test
	public void consultarPagamentoPorCartao() throws Exception {
		
		when(pagamentoService.criar(Mockito.any(PagamentoDTO.class))).thenReturn(pagamento);
		when(pagamentoMapper.mapearListaPagamentoParaPagamentoDTO(Mockito.anyIterableOf(Pagamento.class))).thenReturn(listaPagamentoDTO);
		mockMvc.perform(get("/pagamentos/"+numero))
				.andExpect(status().isOk());
				
	}
	
}
