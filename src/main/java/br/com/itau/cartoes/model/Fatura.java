package br.com.itau.cartoes.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Fatura {

    private Iterable<Pagamento> pagamentos;

    private BigDecimal valorPago;

    private LocalDate dataPagamento;

    public Iterable<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(Iterable<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
