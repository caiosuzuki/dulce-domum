package com.dulcedomum.dominio.familia;

import java.util.Arrays;
import java.util.Optional;

public enum StatusDaFamilia {
    CADASTRO_VALIDO("0", "Cadastro válido"),
    JA_POSSUI_UMA_CASA("1", "Já possui uma casa"),
    SELECIONADA_EM_OUTRO_PROCESSO_DE_SELECAO("2", "Selecionado em outro processo de seleção"),
    CADASTRO_INCOMPLETO("3", "Cadastro incompleto");

    private String codigo;
    private String descricao;

    StatusDaFamilia(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusDaFamilia getEnumPeloCodigo(String codigo) {
        Optional<StatusDaFamilia> statusDaFamiliaEncontrado = Arrays.stream(StatusDaFamilia.values()).filter(statusDaFamilia ->
                statusDaFamilia.getCodigo().equals(codigo)).findAny();
        return statusDaFamiliaEncontrado.orElse(null);
    }
}
