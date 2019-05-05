package com.dulcedomum.dominio.familia.pessoa;

public enum TipoDePessoa {
    PRETENDENTE("pretendente"),
    DEPENDENTE("dependente"),
    CONJUGE("conjuge");

    private String descricao;

    TipoDePessoa(String descricao) {
        this.descricao = descricao;
    }
}
