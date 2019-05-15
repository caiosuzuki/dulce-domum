package com.dulcedomum.dominio.familia.selecao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.StatusDaFamilia;
import com.dulcedomum.dominio.familia.pontuacao.CalculaPontuacaoDaFamilia;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SelecionaFamiliasServicoDeDominioConcreto implements SelecionaFamiliasServicoDeDominio {

    private static final int QUANTIDADE_MAXIMA_DE_FAMILIAS_SELECIONADAS = 3;

    private CalculaPontuacaoDaFamilia calculaPontuacaoDaFamilia;

    @Autowired
    public SelecionaFamiliasServicoDeDominioConcreto(CalculaPontuacaoDaFamilia calculaPontuacaoDaFamilia) {
        this.calculaPontuacaoDaFamilia = calculaPontuacaoDaFamilia;
    }

    @Override
    public List<Familia> selecionar(List<Familia> familias) {
        List<Familia> familiasComCadastroValido = obterFamiliasComCadastroValido(familias);
        List<Pair<Familia, DadosDaSelecaoDaFamilia>> paresDeFamiliasSelecionadasEDadosDaSelecao = obterParesDeFamiliasSelecionadasComPontuacoes(familiasComCadastroValido);
        paresDeFamiliasSelecionadasEDadosDaSelecao.forEach(par -> par.getLeft().setDadosDaSelecao(par.getRight()));
        return paresDeFamiliasSelecionadasEDadosDaSelecao.stream().map(Pair::getLeft).collect(toList());
    }

    private List<Familia> obterFamiliasComCadastroValido(List<Familia> familias) {
        return familias.stream().filter(familia -> familia.getStatus().equals(StatusDaFamilia.CADASTRO_VALIDO)).collect(toList());
    }

    private List<Pair<Familia, DadosDaSelecaoDaFamilia>> obterParesDeFamiliasSelecionadasComPontuacoes(List<Familia> familias) {
        List<Pair<Familia, DadosDaSelecaoDaFamilia>> paresDeFamiliasEDadosDaSelecao = new ArrayList<>();
        familias.stream().forEach(familia -> {
            Integer pontuacaoDaFamilia = calculaPontuacaoDaFamilia.calcular(familia);
            Integer quantidadeDeCriteriosAtendidos = calculaPontuacaoDaFamilia.getQuantidadeDeCriteriosAtendidos();
            DadosDaSelecaoDaFamilia dadosDaSelecaoDaFamilia = DadosDaSelecaoDaFamilia.criar(quantidadeDeCriteriosAtendidos, pontuacaoDaFamilia);
            paresDeFamiliasEDadosDaSelecao.add(Pair.of(familia, dadosDaSelecaoDaFamilia));
        });
        paresDeFamiliasEDadosDaSelecao.sort(Comparator.comparing(parDeFamiliaEDadosDaSelecao -> parDeFamiliaEDadosDaSelecao.getRight().getPontuacao()));
        Collections.reverse(paresDeFamiliasEDadosDaSelecao);

        return paresDeFamiliasEDadosDaSelecao.stream().limit(QUANTIDADE_MAXIMA_DE_FAMILIAS_SELECIONADAS).collect(toList());
    }
}
