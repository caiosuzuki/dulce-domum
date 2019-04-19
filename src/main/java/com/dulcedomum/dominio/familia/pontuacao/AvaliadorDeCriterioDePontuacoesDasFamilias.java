package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;

import java.util.List;
import java.util.Map;

public interface AvaliadorDeCriterioDePontuacoesDasFamilias {
    Map<String , Integer> calcularPontuacoesPeloCriterio(List<Familia> familias);
}
