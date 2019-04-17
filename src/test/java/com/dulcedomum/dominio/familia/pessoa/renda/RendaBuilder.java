package com.dulcedomum.dominio.familia.pessoa;

import java.math.BigDecimal;
import java.util.UUID;

public class RendaBuilder {
    private String pessoaId = UUID.randomUUID().toString();
    private BigDecimal valor = BigDecimal.valueOf(1750.0);

    public static RendaBuilder novo() {
        return new RendaBuilder();
    }

    public Renda criar() {
        return Renda.criar(pessoaId, valor);
    }

    public RendaBuilder comPessoaId(String pessoaId) {
        this.pessoaId = pessoaId;
        return this;
    }

    public RendaBuilder comValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }
}
