package br.com.itau.cartoes.fatura.controller;

import br.com.itau.cartoes.fatura.dto.ExpirarDTO;
import br.com.itau.cartoes.pagamento.dto.PagamentoDTO;
import br.com.itau.cartoes.fatura.dto.PagamentoFaturaDTO;
import br.com.itau.cartoes.pagamento.mapper.PagamentoMapper;
import br.com.itau.cartoes.fatura.model.Fatura;
import br.com.itau.cartoes.fatura.service.FaturaService;
import br.com.itau.cartoes.pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
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
