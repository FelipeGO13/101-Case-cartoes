package br.com.itau.cartoes.controller;

import br.com.itau.cartoes.dto.ExpirarDTO;
import br.com.itau.cartoes.dto.PagamentoDTO;
import br.com.itau.cartoes.dto.PagamentoFaturaDTO;
import br.com.itau.cartoes.mapper.PagamentoMapper;
import br.com.itau.cartoes.model.Fatura;
import br.com.itau.cartoes.service.FaturaService;
import br.com.itau.cartoes.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatura")
public class FaturaController {

    @Autowired
    private FaturaService faturaService;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @GetMapping("/{cliente-id}/{cartao-id}")
    public Iterable<PagamentoDTO> consultarFaturaPorClienteECartao(@PathVariable(name = "cliente-id") int clienteId, @PathVariable(name = "cartao-id") int cartaoId){

        return pagamentoMapper.mapearListaPagamentoParaPagamentoDTO(faturaService.consultaPorClienteECartao(clienteId, cartaoId).getPagamentos());
    }

    @PostMapping("/{cliente-id}/{cartao-id}/pagar")
    public Fatura pagarFatura(@PathVariable(name = "cliente-id") int clienteId, @PathVariable(name = "cartao-id") int cartaoId, @RequestBody PagamentoFaturaDTO pagamentoFaturaDTO){

        return faturaService.pagarFatura(clienteId, cartaoId, pagamentoFaturaDTO);
    }

    @PostMapping("/{cliente-id}/{cartao-id}/expirar")
    public ExpirarDTO expirarCartao(@PathVariable(name = "cliente-id") int clienteId, @PathVariable(name = "cartao-id") int cartaoId, @RequestBody ExpirarDTO expirarDTO){
        return faturaService.expirar(clienteId, cartaoId, expirarDTO);
    }

}
