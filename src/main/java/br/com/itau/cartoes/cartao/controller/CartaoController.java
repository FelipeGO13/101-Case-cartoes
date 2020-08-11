package br.com.itau.cartoes.cartao.controller;

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

import br.com.itau.cartoes.cartao.dto.CartaoDTO;
import br.com.itau.cartoes.cartao.mapper.CartaoMapper;
import br.com.itau.cartoes.cartao.model.Cartao;
import br.com.itau.cartoes.cartao.service.CartaoService;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private CartaoMapper cartaoMapper;
	
	@PostMapping
	public ResponseEntity<?> criar(@Valid @RequestBody CartaoDTO cartaoDTO){
		Cartao cartao = cartaoService.criar(cartaoDTO);
		
		return new ResponseEntity<>(cartaoMapper.mapearCartaoparaCartaoDTO(cartao), HttpStatus.CREATED);
	}

	@PatchMapping("/{numero}")
	public CartaoDTO ativarDesativarCartao(@PathVariable String numero, @RequestBody Cartao cartao) {
		Cartao cartaoSelecionado = cartaoService.editar(numero, cartao);

		return cartaoMapper.mapearCartaoparaCartaoDTO(cartaoSelecionado);

	}

	@GetMapping("/{numero}")
	public CartaoDTO consultaPorNumero(@PathVariable String numero) {
		Optional<Cartao> cartao = cartaoService.consultaPorNumero(numero);

		return cartaoMapper.mapearCartaoparaCartaoDTOSemAtivo(cartao.get());
	}

}
