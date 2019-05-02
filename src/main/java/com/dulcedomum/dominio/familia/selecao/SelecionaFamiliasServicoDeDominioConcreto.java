package com.dulcedomum.dominio.familia.selecao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.StatusDaFamilia;
import com.dulcedomum.dominio.familia.pontuacao.CalculaPontuacaoDaFamilia;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
        List<Familia> familiasSelecionadas = obterQuantidadeMaximaDeFamiliasSelecionadas(familiasComCadastroValido);
        return familiasSelecionadas;
    }

    private List<Familia> obterFamiliasComCadastroValido(List<Familia> familias) {
        return familias.stream().filter(familia -> familia.getStatus().equals(StatusDaFamilia.CADASTRO_VALIDO)).collect(toList());
    }

    private List<Familia> obterQuantidadeMaximaDeFamiliasSelecionadas(List<Familia> familias) {
        familias.sort(Comparator.comparing(familia -> calculaPontuacaoDaFamilia.calcular(familia)));
        Collections.reverse(familias);

        return familias.stream().limit(QUANTIDADE_MAXIMA_DE_FAMILIAS_SELECIONADAS).collect(toList());
    }
}
