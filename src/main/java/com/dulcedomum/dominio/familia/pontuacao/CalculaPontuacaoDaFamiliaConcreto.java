package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculaPontuacaoDaFamiliaConcreto implements CalculaPontuacaoDaFamilia {

    private List<AvaliadorDeCriterioDePontuacaoDaFamilia> avaliadoresDeCriterioDePontuacaoDaFamilia;
    private Integer quantidadeDeCriteriosAtendidos;

    @Autowired
    public CalculaPontuacaoDaFamiliaConcreto(List<AvaliadorDeCriterioDePontuacaoDaFamilia> avaliadoresDeCriterioDePontuacaoDaFamilia) {
        this.avaliadoresDeCriterioDePontuacaoDaFamilia = avaliadoresDeCriterioDePontuacaoDaFamilia;
    }

    @Override
    public Integer calcular(Familia familia) {
        this.quantidadeDeCriteriosAtendidos = 0;
        return avaliadoresDeCriterioDePontuacaoDaFamilia.stream()
                .mapToInt(avaliador -> {
                    Integer pontuacaoNoCriterio = avaliador.calcularPontuacaoPeloCriterio(familia);
                    if (pontuacaoNoCriterio != 0) {
                        this.quantidadeDeCriteriosAtendidos += 1;
                    }
                    return pontuacaoNoCriterio;
                }).sum();
    }

    @Override
    public Integer getQuantidadeDeCriteriosAtendidos() {
        return quantidadeDeCriteriosAtendidos;
    }

}
