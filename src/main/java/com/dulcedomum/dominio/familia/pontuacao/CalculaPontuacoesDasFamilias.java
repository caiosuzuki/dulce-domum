package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;

import java.util.List;
import java.util.Map;

public interface CalculaPontuacoesDasFamilias {
    Map<String, Integer> calcular(List<Familia> familias);
}
