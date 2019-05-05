package com.dulcedomum.aplicacao.comando.familia.adicionafamilia;

import java.math.BigDecimal;

public class RendaDePessoaDaFamilia {

    private final String pessoaId;
    private final BigDecimal valor;

    public RendaDePessoaDaFamilia(String pessoaId, BigDecimal valor) {
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
