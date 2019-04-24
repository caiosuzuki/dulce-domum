package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Component
public class AvaliadorDeCriterioDeIdadeDoPretendente implements AvaliadorDeCriterioDePontuacoesDasFamilias {

    private static final int PONTUACAO_DE_FAVORECIDO = 3;

    @Override
    public Map<String, Integer> calcularPontuacoesPeloCriterio(List<Familia> familias) {
        HashMap<String, Integer> mapaDeFamiliaIdsEPontuacoes = new HashMap<>();

        List<Pessoa> pretendentesDasFamilias = familias.stream().map(familia -> familia.getPretendente().get()).collect(toList());
        Pessoa pretendenteMaisVelho = Collections.max(pretendentesDasFamilias, Comparator.comparing(Pessoa::getIdade));
        String familiaIdDoPretendenteMaisVelho = obterFamiliaIdDoPretendenteMaisVelho(pretendenteMaisVelho, familias);
        mapaDeFamiliaIdsEPontuacoes.put(familiaIdDoPretendenteMaisVelho, PONTUACAO_DE_FAVORECIDO);

        return mapaDeFamiliaIdsEPontuacoes;
    }

    private String obterFamiliaIdDoPretendenteMaisVelho(Pessoa pretendenteMaisVelho, List<Familia> familias) {
        Optional<Familia> familiaDoPretendenteMaisVelho = familias.stream().filter(familia ->
                familia.getPretendente().get().equals(pretendenteMaisVelho)).findAny();
        return familiaDoPretendenteMaisVelho.get().getId();
    }
}
