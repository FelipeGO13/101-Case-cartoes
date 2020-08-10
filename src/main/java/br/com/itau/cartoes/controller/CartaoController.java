package br.com.itau.cartoes.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.cartoes.dto.CartaoDTO;
import br.com.itau.cartoes.model.Cartao;
import br.com.itau.cartoes.service.CartaoService;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;
	
	@PostMapping
	public ResponseEntity<?> criar(@Valid @RequestBody CartaoDTO cartaoDTO){
		Cartao cartao = cartaoService.criar(cartaoDTO);
		
		CartaoDTO cartaoCriado = new CartaoDTO();
		
		cartaoCriado.setId(cartao.getId());
		cartaoCriado.setNumero(cartao.getNumero());
		cartaoCriado.setClientId(cartao.getCliente().getId());
		cartaoCriado.setAtivo(cartao.isAtivo());
		
		return new ResponseEntity<>(cartaoCriado, HttpStatus.CREATED);
	}

	@PatchMapping("/{numero}")
	public CartaoDTO ativarDesativarCartao(@PathVariable String numero, @RequestBody Cartao cartao) {
		Cartao cartaoSelecionado = cartaoService.editar(numero, cartao);

		CartaoDTO cartaoDTO = new CartaoDTO();

		cartaoDTO.setId(cartaoSelecionado.getId());
		cartaoDTO.setNumero(cartaoSelecionado.getNumero());
		cartaoDTO.setClientId(cartaoSelecionado.getCliente().getId());
		cartaoDTO.setAtivo(cartaoSelecionado.isAtivo());

		return cartaoDTO;

	}

	@GetMapping("/{numero}")
	public CartaoDTO consultaPorNumero(@PathVariable String numero) {
		Optional<Cartao> cartao = cartaoService.consultaPorNumero(numero);

		CartaoDTO cartaoDTO = new CartaoDTO();

		cartaoDTO.setId(cartao.get().getId());
		cartaoDTO.setNumero(cartao.get().getNumero());
		cartaoDTO.setClientId(cartao.get().getCliente().getId());

		return cartaoDTO;
	}

}
