package com.dulcedomum.aplicacao.comando.familia.adicionafamilia;

import java.time.LocalDate;

public class PessoaDaFamilia {

    private final String nome;
    private final String tipo;
    private final LocalDate dataDeNascimento;

    public PessoaDaFamilia(String nome, String tipo, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.tipo = tipo;
        this.dataDeNascimento = dataDeNascimento;
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
}
