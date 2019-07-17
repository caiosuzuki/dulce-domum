package com.dulcedomum.dominio.familia.pessoa;

import java.util.Arrays;
import java.util.Optional;

public enum TipoDePessoa {
    PRETENDENTE("Pretendente"),
    DEPENDENTE("Dependente"),
    CONJUGE("Cônjuge");

    private String descricao;

    TipoDePessoa(String descricao) {
        this.descricao = descricao;
    }

    public static TipoDePessoa getEnumPelaDescricao(String descricao) {
        Optional<TipoDePessoa> tipoDePessoaEncontrado = Arrays.stream(TipoDePessoa.values()).filter(tipoDePessoa ->
                tipoDePessoa.getDescricao().equals(descricao)).findAny();
        return tipoDePessoaEncontrado.orElse(null);
    }

    public String getDescricao() {
        return descricao;
    }
}
