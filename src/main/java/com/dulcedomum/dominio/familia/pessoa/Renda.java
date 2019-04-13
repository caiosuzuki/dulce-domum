package com.dulcedomum.dominio.familia.pessoa;

import java.math.BigDecimal;

public class Renda {

    private String pessoaId;
    private BigDecimal valor;

    public Renda(String pessoaId, BigDecimal valor) {
        this.pessoaId = pessoaId;
        this.valor = valor;
    }

    public String getPessoaId() {
        return pessoaId;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
