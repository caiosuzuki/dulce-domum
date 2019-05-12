package com.dulcedomum.dominio.familia.pessoa.renda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Renda {

    public Renda() {
    }

    @Id
    @GeneratedValue
    private Integer idDoRepositorio;

    private String pessoaId;
    private BigDecimal valor;

    private Renda(String pessoaId, BigDecimal valor) {
        this.pessoaId = pessoaId;
        this.valor = valor;
    }

    public static Renda criar(String pessoaId, BigDecimal valor) {
        validarCamposObrigatorios(pessoaId, valor);
        return new Renda(pessoaId, valor);
    }

    private static void validarCamposObrigatorios(String pessoaId, BigDecimal valor) {
        if (pessoaId == null || pessoaId.isEmpty()) {
            throw new IllegalArgumentException("É necessário informar o identificador da pessoa para registrar sua renda.");
        }
        if (valor == null) {
            throw new IllegalArgumentException("É necessário informar o valor da renda da pessoa.");
        }
        if(valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da renda de uma pessoa deve ser maior que zero.");
        }
    }

    public String getPessoaId() {
        return pessoaId;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
