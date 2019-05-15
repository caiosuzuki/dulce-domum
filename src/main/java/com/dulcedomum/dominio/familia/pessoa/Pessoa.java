package com.dulcedomum.dominio.familia.pessoa;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private LocalDate dataDeNascimento;
    private BigDecimal valorDaRenda;

    @Enumerated(EnumType.STRING)
    private TipoDePessoa tipo;

    public Pessoa(String nome, TipoDePessoa tipo, LocalDate dataDeNascimento, BigDecimal valorDaRenda) {
        this.nome = nome;
        this.tipo = tipo;
        this.dataDeNascimento = dataDeNascimento;
        this.valorDaRenda = valorDaRenda;
    }

    public static Pessoa criar(String nome, TipoDePessoa tipo, LocalDate dataDeNascimento, BigDecimal valorDaRenda) {
        validarCamposObrigatorios(nome, tipo, dataDeNascimento, valorDaRenda);
        return new Pessoa(nome, tipo, dataDeNascimento, valorDaRenda);
    }

    private static void validarCamposObrigatorios(String nome, TipoDePessoa tipo, LocalDate dataDeNascimento, BigDecimal valorDaRenda) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("É necessário informar o nome da pessoa para registrá-la.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("É necessário informar o tipo da pessoa para registrá-la.");
        }
        if (dataDeNascimento == null) {
            throw new IllegalArgumentException("É necessário informar a data de nascimento da pessoa para registrá-la.");
        }
        if (valorDaRenda == null) {
            throw new IllegalArgumentException("É necessário informar o valor da renda da pessoa para registrá-la.");
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

    public Integer getIdade() {
        return Period.between(this.dataDeNascimento, LocalDate.now()).getYears();
    }

    public BigDecimal getValorDaRenda() {
        return valorDaRenda;
    }
}
