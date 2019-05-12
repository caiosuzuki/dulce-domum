package com.dulcedomum.dominio.familia.pessoa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Pessoa {

    public Pessoa() {
    }

    @Id
    @GeneratedValue
    private Integer idDoRepositorio;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoDePessoa tipo;

    private LocalDate dataDeNascimento;

    private Pessoa(String nome, TipoDePessoa tipo, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.tipo = tipo;
        this.dataDeNascimento = dataDeNascimento;
    }

    private static void validarCamposObrigatorios(String nome, TipoDePessoa tipo, LocalDate dataDeNascimento) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("É necessário informar o nome da pessoa para registrá-la.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("É necessário informar o tipo da pessoa para registrá-la.");
        }
        if (dataDeNascimento == null) {
            throw new IllegalArgumentException("É necessário informar a data de nascimento da pessoa para registrá-la.");
        }
    }

    public static Pessoa criar(String nome, TipoDePessoa tipo, LocalDate dataDeNascimento) {
        validarCamposObrigatorios(nome, tipo, dataDeNascimento);
        return new Pessoa(nome, tipo, dataDeNascimento);
    }

    public String getNome() {
        return nome;
    }

    public TipoDePessoa getTipo() {
        return tipo;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public Integer getIdade() {
        return Period.between(this.dataDeNascimento, LocalDate.now()).getYears();
    }
}
