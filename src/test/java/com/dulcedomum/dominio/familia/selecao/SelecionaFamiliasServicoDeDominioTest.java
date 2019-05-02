package com.dulcedomum.dominio.familia.selecao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import com.dulcedomum.dominio.familia.StatusDaFamilia;
import com.dulcedomum.dominio.familia.pontuacao.CalculaPontuacaoDaFamilia;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SelecionaFamiliasServicoDeDominioTest {

    private static final int QUANTIDADE_MAXIMA_DE_FAMILIAS_SELECIONADAS = 3;

    private SelecionaFamiliasServicoDeDominio selecionaFamiliasServicoDeDominio;
    private CalculaPontuacaoDaFamilia calculaPontuacaoDaFamilia;

    @Before
    public void setUp() {
        calculaPontuacaoDaFamilia = mock(CalculaPontuacaoDaFamilia.class);
        selecionaFamiliasServicoDeDominio = new SelecionaFamiliasServicoDeDominioConcreto(calculaPontuacaoDaFamilia);
    }

    @Test
    public void deveSelecionarAsFamiliasPelaPontuacaoDosCriterios() {
        Familia primeiraFamilia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        Familia segundaFamilia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        Familia terceiraFamilia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        List<Familia> familias = asList(primeiraFamilia, segundaFamilia, terceiraFamilia);
        when(calculaPontuacaoDaFamilia.calcular(any())).thenReturn(10);

        List<Familia> familiasSelecionadas = selecionaFamiliasServicoDeDominio.selecionar(familias);

        assertThat(familiasSelecionadas).hasSize(QUANTIDADE_MAXIMA_DE_FAMILIAS_SELECIONADAS);
        assertThat(familiasSelecionadas).containsExactlyInAnyOrder(primeiraFamilia, segundaFamilia, terceiraFamilia);
    }

    @Test
    public void deveSelecionarApenasAQuantidadeDeFamiliasMaximaOrdenandoPelaPontuacao() {
        Familia primeiraFamiliaClassificada = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        Familia segundaFamiliaClassificada = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        Familia terceiraFamiliaClassificada = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        Familia familiaDeMenorPontuacao = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        List<Familia> familias = asList(primeiraFamiliaClassificada, segundaFamiliaClassificada, terceiraFamiliaClassificada, familiaDeMenorPontuacao);
        when(calculaPontuacaoDaFamilia.calcular(primeiraFamiliaClassificada)).thenReturn(10);
        when(calculaPontuacaoDaFamilia.calcular(segundaFamiliaClassificada)).thenReturn(9);
        when(calculaPontuacaoDaFamilia.calcular(terceiraFamiliaClassificada)).thenReturn(8);
        when(calculaPontuacaoDaFamilia.calcular(familiaDeMenorPontuacao)).thenReturn(7);

        List<Familia> familiasSelecionadas = selecionaFamiliasServicoDeDominio.selecionar(familias);

        assertThat(familiasSelecionadas).hasSize(QUANTIDADE_MAXIMA_DE_FAMILIAS_SELECIONADAS);
        assertThat(familiasSelecionadas).containsSequence(primeiraFamiliaClassificada, segundaFamiliaClassificada, terceiraFamiliaClassificada);
    }

    @Test
    public void deveRemoverDaListagemFinalAsFamiliasQueNaoEstaoComCadastroValido() {
        Familia familiaComCadastroValido = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        Familia familiaComCadastroIncompleto = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_INCOMPLETO).criar();
        Familia familiaQueJaPossuiCasa = FamiliaBuilder.novo().comStatus(StatusDaFamilia.JA_POSSUI_UMA_CASA).criar();
        Familia familiaSelecionadaEmOutroProcesso = FamiliaBuilder.novo().comStatus(StatusDaFamilia.SELECIONADA_EM_OUTRO_PROCESSO_DE_SELECAO).criar();
        List<Familia> familias = asList(familiaComCadastroValido, familiaComCadastroIncompleto, familiaQueJaPossuiCasa, familiaSelecionadaEmOutroProcesso);
        int pontuacaoDaFamiliaSelecionada = 5;
        when(calculaPontuacaoDaFamilia.calcular(any()))
                .thenReturn(pontuacaoDaFamiliaSelecionada);

        List<Familia> familiasSelecionadas = selecionaFamiliasServicoDeDominio.selecionar(familias);

        assertThat(familiasSelecionadas).hasSize(1);
        assertThat(familiasSelecionadas).containsOnly(familiaComCadastroValido);
    }

    @Test
    public void deveAtualizarOsDadosDaSelecaoDaFamiliaSelecionada() {
        int quantidadeDeCriteriosAtendidosEsperada = 3;
        int pontuacaoEsperada = 10;
        LocalDate hoje = LocalDate.now();
        Familia familia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        when(calculaPontuacaoDaFamilia.calcular(any()))
                .thenReturn(pontuacaoEsperada);
        when(calculaPontuacaoDaFamilia.getQuantidadeDeCriteriosAtendidos())
                .thenReturn(quantidadeDeCriteriosAtendidosEsperada);

        List<Familia> familiasSelecionadas = selecionaFamiliasServicoDeDominio.selecionar(singletonList(familia));
        DadosDaSelecaoDaFamilia dadosDaSelecaoDaFamilia = familiasSelecionadas.get(0).getDadosDaSelecao();

        assertThat(dadosDaSelecaoDaFamilia.getQuantidadeDeCriteriosAtendidos()).isEqualTo(quantidadeDeCriteriosAtendidosEsperada);
        assertThat(dadosDaSelecaoDaFamilia.getPontuacao()).isEqualTo(pontuacaoEsperada);
        assertThat(dadosDaSelecaoDaFamilia.getDataDaSelecao()).isEqualTo(hoje);
    }
}
