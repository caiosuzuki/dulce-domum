package com.dulcedomum.dominio.familia.selecao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import com.dulcedomum.dominio.familia.StatusDaFamilia;
import com.dulcedomum.dominio.familia.pontuacao.CalculaPontuacoesDasFamilias;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessoDeSelecaoTest {

    private ProcessoDeSelecao processoDeSelecao;
    private CalculaPontuacoesDasFamilias calculaPontuacoesDasFamilias;

    @Before
    public void setUp() throws Exception {
        calculaPontuacoesDasFamilias = mock(CalculaPontuacoesDasFamilias.class);
        processoDeSelecao = new ProcessoDeSelecao(calculaPontuacoesDasFamilias);
    }

    @Test
    public void deveSelecionarAsFamiliasPelaPontuacaoDosCriterios() {
        Familia primeiraFamilia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        Familia segundaFamilia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        Familia terceiraFamilia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        List<Familia> familias = asList(primeiraFamilia, segundaFamilia, terceiraFamilia);
        List<Integer> pontuacoes = asList(7, 3, 1);
        Map<String, Integer> mapaDeFamiliaIdsEPontuacoesCalculadas = montarMapaDeFamiliaIdEPontuacoes(familias, pontuacoes);
        when(calculaPontuacoesDasFamilias.calcular(familias)).thenReturn(mapaDeFamiliaIdsEPontuacoesCalculadas);

        Map<String, Integer> mapaDeFamiliasSelecionado = processoDeSelecao.selecionar(familias);

        assertThat(mapaDeFamiliasSelecionado.get(primeiraFamilia.getId())).isEqualTo(mapaDeFamiliaIdsEPontuacoesCalculadas.get(primeiraFamilia.getId()));
        assertThat(mapaDeFamiliasSelecionado.get(segundaFamilia.getId())).isEqualTo(mapaDeFamiliaIdsEPontuacoesCalculadas.get(segundaFamilia.getId()));
        assertThat(mapaDeFamiliasSelecionado.get(terceiraFamilia.getId())).isEqualTo(mapaDeFamiliaIdsEPontuacoesCalculadas.get(terceiraFamilia.getId()));
    }

    @Test
    public void deveRemoverDaListagemFinalAsFamiliasQueNaoEstaoComCadastroValido() {
        Familia familiaComCadastroValido = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        Familia familiaComCadastroIncompleto = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_INCOMPLETO).criar();
        Familia familiaQueJaPossuiCasa = FamiliaBuilder.novo().comStatus(StatusDaFamilia.JA_POSSUI_UMA_CASA).criar();
        Familia familiaSelecionadaEmOutroProcesso = FamiliaBuilder.novo().comStatus(StatusDaFamilia.SELECIONADA_EM_OUTRO_PROCESSO_DE_SELECAO).criar();
        List<Familia> familias = asList(familiaComCadastroValido, familiaComCadastroIncompleto, familiaQueJaPossuiCasa, familiaSelecionadaEmOutroProcesso);
        List<Familia> familiasQueNaoForamDesclassificadas = singletonList(familiaComCadastroValido);
        int pontuacaoDaFamiliaClassificada = 5;
        Map<String, Integer> mapaDeFamiliaIdsEPontuacoesCalculadas = montarMapaDeFamiliaIdEPontuacoes(familias, singletonList(pontuacaoDaFamiliaClassificada));
        when(calculaPontuacoesDasFamilias.calcular(familiasQueNaoForamDesclassificadas))
                .thenReturn(mapaDeFamiliaIdsEPontuacoesCalculadas);

        Map<String, Integer> mapaDeFamiliasSelecionado = processoDeSelecao.selecionar(familias);

        assertThat(mapaDeFamiliasSelecionado).hasSize(1);
        assertThat(mapaDeFamiliasSelecionado.get(familiaComCadastroValido.getId())).isEqualTo(mapaDeFamiliaIdsEPontuacoesCalculadas.get(familiaComCadastroValido.getId()));
    }

    @Test
    public void deveEncerrarOProcessoDeSelecao() {
        processoDeSelecao.encerrar();

        assertThat(processoDeSelecao.estaEncerrado()).isTrue();
    }

    @Test
    public void devePreencherADataDeEncerramentoComADataAtualAoEncerrarOProcessoDeSelecao() {
        processoDeSelecao.encerrar();

        assertThat(processoDeSelecao.getDataDeEncerramento()).isEqualTo(LocalDate.now());
    }

    private Map<String, Integer> montarMapaDeFamiliaIdEPontuacoes(List<Familia> familias,
                                                                  List<Integer> pontuacoes) {
        Map<String, Integer> mapaDeFamiliaIdsEPontuacoes = new HashMap<>();
        for (int indiceDePontuacao = 0; indiceDePontuacao < pontuacoes.size(); indiceDePontuacao++) {
            mapaDeFamiliaIdsEPontuacoes.put(familias.get(indiceDePontuacao).getId(), pontuacoes.get(indiceDePontuacao));
        }
        return mapaDeFamiliaIdsEPontuacoes;
    }
}
