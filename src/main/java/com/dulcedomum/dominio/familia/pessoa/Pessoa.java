package com.dulcedomum.dominio.familia.pessoa;

import java.time.LocalDate;
import java.time.Period;

public class Pessoa {

    private String id;
    private String nome;
    private TipoDePessoa tipo;
    private LocalDate dataDeNascimento;

    private Pessoa(String id, String nome, TipoDePessoa tipo, LocalDate dataDeNascimento) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.dataDeNascimento = dataDeNascimento;
    }

    public static Pessoa criar(String id, String nome, TipoDePessoa tipo, LocalDate dataDeNascimento) {
        validarCamposObrigatorios(id, nome, tipo, dataDeNascimento);
        return new Pessoa(id, nome, tipo, dataDeNascimento);
    }

    private static void validarCamposObrigatorios(String id, String nome, TipoDePessoa tipo, LocalDate dataDeNascimento) {
        if (id == null) {
            throw new IllegalArgumentException("Não é possível registrar uma pessoa com identificador inválido.");
        }
        if (id.isEmpty()) {
            throw new IllegalArgumentException("Não é possível registrar uma pessoa com identificador vazio.");
        }
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

    public String getNome() {
        return nome;
    }

    public TipoDePessoa getTipo() {
        return tipo;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String id() {
        return id;
    }

    public int getIdade() {
        return Period.between(this.dataDeNascimento, LocalDate.now()).getYears();
    }
}
