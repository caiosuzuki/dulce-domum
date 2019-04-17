package com.dulcedomum.dominio.familia;

import java.util.List;

public class CalculaPontuacaoDaFamiliaConcreto implements CalculaPontuacaoDaFamilia {

    private List<AvaliadorDeCriterioDePontuacaoDaFamilia> avaliadoresDeCriterioDePontuacaoDaFamilia;

    public CalculaPontuacaoDaFamiliaConcreto(List<AvaliadorDeCriterioDePontuacaoDaFamilia> avaliadoresDeCriterioDePontuacaoDaFamilia) {
        this.avaliadoresDeCriterioDePontuacaoDaFamilia = avaliadoresDeCriterioDePontuacaoDaFamilia;
    }

    @Override
    public int calcular(Familia familia) {
        return avaliadoresDeCriterioDePontuacaoDaFamilia.stream()
                .mapToInt(avaliadorDeCriterio -> avaliadorDeCriterio.calcularPontuacaoPeloCriterio(familia))
                .sum();
    }
}
