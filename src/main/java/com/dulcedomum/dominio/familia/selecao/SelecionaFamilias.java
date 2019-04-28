package com.dulcedomum.dominio.familia.selecao;

import com.dulcedomum.dominio.familia.Familia;

import java.util.List;
import java.util.Map;

public interface SelecionaFamilias {
    Map<String, Integer> selecionar(List<Familia> familias);
}
