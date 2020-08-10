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
		return new ResponseEntity<>(cartaoService.criar(cartaoDTO), HttpStatus.CREATED);
	}
	
	@PatchMapping("/{numero}")
	public Cartao ativarDesativarCartao(@PathVariable String numero, @RequestBody Cartao cartao) {
		return cartaoService.editar(numero, cartao);
	}
	
	@GetMapping("/{numero}")
	public Optional<Cartao> consultaPorNumero(@PathVariable String numero){
		return cartaoService.consultaPorNumero(numero);
	}
	
}
