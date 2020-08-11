package br.com.itau.cartoes.cliente.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.cartoes.cliente.model.Cliente;
import br.com.itau.cartoes.cliente.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping(produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> criar(@Valid @RequestBody Cliente cliente) {
		
		return new ResponseEntity<>(clienteService.criar(cliente), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public Optional<Cliente> consultarPorId(@PathVariable int id){
		return clienteService.buscar(id);
	}
	
}
