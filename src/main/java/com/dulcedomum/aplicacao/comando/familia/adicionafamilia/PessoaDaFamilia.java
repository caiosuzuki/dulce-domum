package com.dulcedomum.aplicacao.comando.familia.adicionafamilia;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PessoaDaFamilia {

    private final String nome;
    private final String tipo;
    private final LocalDate dataDeNascimento;
    private BigDecimal valorDaRenda;

    public PessoaDaFamilia(String nome, String tipo, LocalDate dataDeNascimento, BigDecimal valorDaRenda) {
        this.nome = nome;
        this.tipo = tipo;
        this.dataDeNascimento = dataDeNascimento;
        this.valorDaRenda = valorDaRenda;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public BigDecimal getValorDaRenda() {
        return valorDaRenda;
    }
}
