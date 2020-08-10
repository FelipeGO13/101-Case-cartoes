package br.com.itau.cartoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.itau.cartoes.mapper.CartaoMapper;
import br.com.itau.cartoes.mapper.PagamentoMapper;

@SpringBootApplication
public class CartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartoesApplication.class, args);
	}

	@Bean
	public PagamentoMapper pagamentoMapper() {
		return new PagamentoMapper();
	}
	
	@Bean
	public CartaoMapper cartaoMapper() {
		return new CartaoMapper();
	}
}
