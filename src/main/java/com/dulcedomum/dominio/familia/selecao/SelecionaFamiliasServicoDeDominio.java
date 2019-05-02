package com.dulcedomum.dominio.familia.selecao;

import com.dulcedomum.dominio.familia.Familia;

import java.util.List;

public interface SelecionaFamiliasServicoDeDominio {
    List<Familia> selecionar(List<Familia> familias);
}
