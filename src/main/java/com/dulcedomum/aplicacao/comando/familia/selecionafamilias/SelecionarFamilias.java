package com.dulcedomum.aplicacao.comando.familia.selecionafamilias;

import com.dulcedomum.aplicacao.comando.base.Comando;

import java.util.List;

public class SelecionarFamilias implements Comando {

    List<String> idsDasFamilias;

    public SelecionarFamilias(List<String> idsDasFamilias) {
        this.idsDasFamilias = idsDasFamilias;
    }

    public List<String> getIdsDasFamilias() {
        return idsDasFamilias;
    }
}
