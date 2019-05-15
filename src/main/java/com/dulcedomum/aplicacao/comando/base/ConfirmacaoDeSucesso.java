package com.dulcedomum.aplicacao.comando.base;

public class ConfirmacaoDeSucesso {

    private String identificador;

    private ConfirmacaoDeSucesso(String identificador) {
        this.identificador = identificador;
    }

    public static ConfirmacaoDeSucesso criar(String identificador) {
        return new ConfirmacaoDeSucesso(identificador);
    }

    public static ConfirmacaoDeSucesso criar() {
        return criar("");
    }

    public String getId() {
        return identificador;
    }
}
