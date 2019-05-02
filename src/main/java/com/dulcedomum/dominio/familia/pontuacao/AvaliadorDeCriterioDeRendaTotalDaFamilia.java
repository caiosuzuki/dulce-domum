package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AvaliadorDeCriterioDeRendaTotalDaFamilia implements AvaliadorDeCriterioDePontuacaoDaFamilia {

    private static final int VALOR_LIMITE_PARA_PONTUAR_5_PONTOS = 900;
    private static final int VALOR_LIMITE_PARA_PONTUAR_3_PONTOS = 1500;
    private static final int VALOR_LIMITE_PARA_PONTUAR_1_PONTO = 2000;
    private static final int CINCO_PONTOS = 5;
    private static final int TRES_PONTOS = 3;
    private static final int UM_PONTO = 1;
    private static final int ZERO_PONTOS = 0;

    @Override
    public Integer calcularPontuacaoPeloCriterio(Familia familia) {

        if (familia.calcularValorTotalDaRenda().compareTo(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_5_PONTOS)) <= 0) {
            return CINCO_PONTOS;
        } else if (familia.calcularValorTotalDaRenda().compareTo(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_3_PONTOS)) <= 0) {
            return TRES_PONTOS;
        } else if (familia.calcularValorTotalDaRenda().compareTo(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_1_PONTO)) <= 0) {
            return UM_PONTO;
        } else {
            return ZERO_PONTOS;
        }
    }
}
