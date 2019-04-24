package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AvaliadorDeCriterioDeRendaTotalDaFamilia implements AvaliadorDeCriterioDePontuacoesDasFamilias {

    private static final int VALOR_LIMITE_PARA_PONTUAR_5_PONTOS = 900;
    private static final int VALOR_LIMITE_PARA_PONTUAR_3_PONTOS = 1500;
    private static final int VALOR_LIMITE_PARA_PONTUAR_1_PONTO = 2000;

    @Override
    public Map<String, Integer> calcularPontuacoesPeloCriterio(List<Familia> familias) {
        HashMap<String, Integer> mapaDeFamiliaIdEPontuacoes = new HashMap<>();
        familias.forEach(familia -> {
            if (familia.calcularValorTotalDaRenda().compareTo(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_5_PONTOS)) <= 0) {
                mapaDeFamiliaIdEPontuacoes.put(familia.getId(), 5);
            } else if (familia.calcularValorTotalDaRenda().compareTo(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_3_PONTOS)) <= 0) {
                mapaDeFamiliaIdEPontuacoes.put(familia.getId(), 3);
            } else if (familia.calcularValorTotalDaRenda().compareTo(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_1_PONTO)) <= 0) {
                mapaDeFamiliaIdEPontuacoes.put(familia.getId(), 1);
            } else {
                mapaDeFamiliaIdEPontuacoes.put(familia.getId(), 0);
            }
        });
        return mapaDeFamiliaIdEPontuacoes;
    }
}
