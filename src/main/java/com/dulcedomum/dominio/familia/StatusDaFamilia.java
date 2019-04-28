package com.dulcedomum.dominio.familia;

public enum StatusDaFamilia {
    CADASTRO_VALIDO(0),
    JA_POSSUI_UMA_CASA(1),
    SELECIONADA_EM_OUTRO_PROCESSO_DE_SELECAO(2),
    CADASTRO_INCOMPLETO(3);

    private int codigo;

    StatusDaFamilia (int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
