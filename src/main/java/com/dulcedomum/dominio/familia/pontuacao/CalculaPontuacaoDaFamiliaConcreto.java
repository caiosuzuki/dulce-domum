package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CalculaPontuacaoDaFamiliaConcreto implements CalculaPontuacaoDaFamilia {

    private List<AvaliadorDeCriterioDePontuacaoDaFamilia> avaliadoresDeCriterioDePontuacaoDaFamilia;

    @Autowired
    public CalculaPontuacaoDaFamiliaConcreto(List<AvaliadorDeCriterioDePontuacaoDaFamilia> avaliadoresDeCriterioDePontuacaoDaFamilia) {
        this.avaliadoresDeCriterioDePontuacaoDaFamilia = avaliadoresDeCriterioDePontuacaoDaFamilia;
    }

    @Override
    public Integer calcular(Familia familia) {
        return avaliadoresDeCriterioDePontuacaoDaFamilia.stream()
                .mapToInt(avaliador -> avaliador.calcularPontuacaoPeloCriterio(familia)).sum();
    }

}
