package com.dulcedomum.dominio.familia.pessoa;

import java.time.LocalDate;
import java.util.UUID;

public class PessoaBuilder {

    private String id = UUID.randomUUID().toString();
    private String nome = "Neide Clovis";
    private TipoDePessoa tipo = TipoDePessoa.PRETENDENTE;
    private LocalDate dataDeNascimento = LocalDate.of(1995, 5, 25);

    public Pessoa criar() {
        return Pessoa.criar(id, nome, tipo, dataDeNascimento);
    }

    public static PessoaBuilder novo() {
        return new PessoaBuilder();
    }

    public PessoaBuilder comTipo(TipoDePessoa tipo) {
        this.tipo = tipo;
        return this;
    }
}
