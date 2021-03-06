package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;

public interface CalculaPontuacaoDaFamilia {
    Integer calcular(Familia familia);
    Integer getQuantidadeDeCriteriosAtendidos();
}
