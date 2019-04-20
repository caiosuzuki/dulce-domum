package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.max;

public class AvaliadorDeCriterioDeQuantidadeDeDependentes implements AvaliadorDeCriterioDePontuacoesDasFamilias {
    private static final Integer PONTUACAO_DE_FAVORECIDO = 3;
    private static final Integer DEZOITO_ANOS = 18;

    @Override
    public Map<String, Integer> calcularPontuacoesPeloCriterio(List<Familia> familias) {
        HashMap<String, Integer> mapaDeFamiliaIdsEPontuacoes = criarMapaComPontuacoesZeradas(familias);

        HashMap<String, Integer> mapaDeQuantidadeDeDependentesPorFamilia = new HashMap<>();

        familias.stream().forEach(familia -> {
            Integer quantidadeDeDependentesMenoresDe18Anos = obterQuantidadeDeDependentesMenoresDe18Anos(familia);
            mapaDeQuantidadeDeDependentesPorFamilia.put(familia.getId(), quantidadeDeDependentesMenoresDe18Anos);
        });

        String idDaFamiliaComMaisDependentes = obterFamiliaIdDaFamiliaComMaisDependentes(mapaDeQuantidadeDeDependentesPorFamilia);
        mapaDeFamiliaIdsEPontuacoes.put(idDaFamiliaComMaisDependentes, PONTUACAO_DE_FAVORECIDO);

        return mapaDeFamiliaIdsEPontuacoes;
    }

    private HashMap<String, Integer> criarMapaComPontuacoesZeradas(List<Familia> familias) {
        HashMap<String, Integer> mapaDeFamiliaIdsEPontuacoesZeradas = new HashMap<>();
        familias.parallelStream().forEach(familia -> mapaDeFamiliaIdsEPontuacoesZeradas.put(familia.getId(), 0));
        return mapaDeFamiliaIdsEPontuacoesZeradas;
    }

    private Integer obterQuantidadeDeDependentesMenoresDe18Anos(Familia familia) {
        return familia.getDependentes().stream().filter(pessoa ->
                pessoa.getIdade().compareTo(DEZOITO_ANOS) < 0).collect(Collectors.toList()).size();
    }

    private String obterFamiliaIdDaFamiliaComMaisDependentes(HashMap<String, Integer> mapaDeQuantidadeDeDependentesPorFamilia) {
        return max(mapaDeQuantidadeDeDependentesPorFamilia.entrySet(), Comparator.comparing(Map.Entry::getValue)).getKey();
    }
}
