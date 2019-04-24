package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculaPontuacoesDasFamiliasConcreto implements CalculaPontuacoesDasFamilias {

    private List<AvaliadorDeCriterioDePontuacoesDasFamilias> avaliadoresDeCriterioDePontuacaoDaFamilia;

    @Autowired
    public CalculaPontuacoesDasFamiliasConcreto(List<AvaliadorDeCriterioDePontuacoesDasFamilias> avaliadoresDeCriterioDePontuacaoDaFamilia) {
        this.avaliadoresDeCriterioDePontuacaoDaFamilia = avaliadoresDeCriterioDePontuacaoDaFamilia;
    }

    @Override
    public Map<String, Integer> calcular(List<Familia> familias) {
        HashMap<String, Integer> mapaDeFamiliaIdsEPontuacoes = new HashMap<>();
        avaliadoresDeCriterioDePontuacaoDaFamilia.stream().forEach(avaliadorDeCriterioDePontuacoesDasFamilias -> {
            Map<String, Integer> mapaPontuacoesDoAvaliador = avaliadorDeCriterioDePontuacoesDasFamilias.calcularPontuacoesPeloCriterio(familias);
            mapaPontuacoesDoAvaliador.forEach((familiaId, pontuacao) -> {
                Integer novaPontuacao = calcularNovaPontuacaoDaFamilia(mapaDeFamiliaIdsEPontuacoes, mapaPontuacoesDoAvaliador, familiaId);
                mapaDeFamiliaIdsEPontuacoes.put(familiaId, novaPontuacao);
            });
        });
        return mapaDeFamiliaIdsEPontuacoes;
    }

    private Integer calcularNovaPontuacaoDaFamilia(HashMap<String, Integer> mapaDeFamiliaIdsEPontuacoes,
                                                   Map<String, Integer> mapaPontuacoesDoAvaliador,
                                                   String familiaId) {
        Integer pontuacaoJaExistente = mapaDeFamiliaIdsEPontuacoes.getOrDefault(familiaId, 0);
        return (pontuacaoJaExistente + mapaPontuacoesDoAvaliador.get(familiaId));
    }
}
