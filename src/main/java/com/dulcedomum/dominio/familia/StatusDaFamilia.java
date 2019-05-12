package com.dulcedomum.dominio.familia;

import java.util.Arrays;
import java.util.Optional;

public enum StatusDaFamilia {
    CADASTRO_VALIDO("0"),
    JA_POSSUI_UMA_CASA("1"),
    SELECIONADA_EM_OUTRO_PROCESSO_DE_SELECAO("2"),
    CADASTRO_INCOMPLETO("3");

    private String codigo;

    StatusDaFamilia(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static StatusDaFamilia getEnumPeloCodigo(String codigo) {
        Optional<StatusDaFamilia> statusDaFamiliaEncontrado = Arrays.stream(StatusDaFamilia.values()).filter(statusDaFamilia ->
                statusDaFamilia.getCodigo().equals(codigo)).findAny();
        return statusDaFamiliaEncontrado.orElse(null);
    }
}
