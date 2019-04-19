package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;

import java.math.BigDecimal;

public class AvaliadorDeCriterioDeRendaTotalDaFamilia implements AvaliadorDeCriterioDePontuacaoDaFamilia {

    private static final int VALOR_LIMITE_PARA_PONTUAR_5_PONTOS = 900;
    private static final int VALOR_LIMITE_PARA_PONTUAR_3_PONTOS = 1500;
    private static final int VALOR_LIMITE_PARA_PONTUAR_1_PONTO = 2000;

    @Override
    public int calcularPontuacaoPeloCriterio(Familia familia) {
        if (familia.calcularValorTotalDaRenda().compareTo(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_5_PONTOS)) <= 0) {
            return 5;
        } else if (familia.calcularValorTotalDaRenda().compareTo(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_3_PONTOS)) <= 0) {
            return 3;
        } else if (familia.calcularValorTotalDaRenda().compareTo(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_1_PONTO)) <= 0) {
            return 1;
        }
        return 0;
    }
}
