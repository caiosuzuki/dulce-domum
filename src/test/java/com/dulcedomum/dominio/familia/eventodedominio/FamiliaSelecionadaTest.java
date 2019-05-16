package com.dulcedomum.dominio.familia.eventodedominio;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FamiliaSelecionadaTest {

    private String familiaId;
    private Integer quantidadeDeCriteriosAtendidos;
    private Integer pontuacaoTotal;
    private LocalDate dataDaSelecao;

    @Before
    public void setUp() {
        familiaId = UUID.randomUUID().toString();
        quantidadeDeCriteriosAtendidos = 3;
        pontuacaoTotal = 10;
        dataDaSelecao = LocalDate.now();
    }

    @Test
    public void deveCriarUmEventoDeFamiliaSelecionada() {
        FamiliaSelecionada familiaSelecionada = FamiliaSelecionada.criar(familiaId, quantidadeDeCriteriosAtendidos, pontuacaoTotal, dataDaSelecao);

        assertThat(familiaSelecionada.getFamiliaId()).isEqualTo(familiaId);
        assertThat(familiaSelecionada.getQuantidadeDeCriteriosAtendidos()).isEqualTo(quantidadeDeCriteriosAtendidos);
        assertThat(familiaSelecionada.getPontuacaoTotal()).isEqualTo(pontuacaoTotal);
        assertThat(familiaSelecionada.getDataDaSelecao()).isEqualTo(dataDaSelecao);
    }

    @Test
    public void naoDeveCriarOEventoCasoNaoSejaInformadoFamiliaId() {
        String familiaId = null;

        Throwable excecaoLancada = catchThrowable(() -> FamiliaSelecionada.criar(familiaId, quantidadeDeCriteriosAtendidos, pontuacaoTotal, dataDaSelecao));

        assertThat(excecaoLancada)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É obrigatório informar o identificador da família para criar o evento de família selecionada.");
    }

    @Test
    public void naoDeveCriarOEventoCasoNaoSejaInformadoQuantidadeDeCriteriosAtendidos() {
        Integer quantidadeDeCriteriosAtendidos = null;

        Throwable excecaoLancada = catchThrowable(() -> FamiliaSelecionada.criar(familiaId, quantidadeDeCriteriosAtendidos, pontuacaoTotal, dataDaSelecao));

        assertThat(excecaoLancada)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É obrigatório informar a quantidade de critérios atendidos da família para criar o evento de família selecionada.");
    }

    @Test
    public void naoDeveCriarOEventoCasoNaoSejaInformadaAPontuacao() {
        Integer pontuacaoTotal = null;

        Throwable excecaoLancada = catchThrowable(() -> FamiliaSelecionada.criar(familiaId, quantidadeDeCriteriosAtendidos, pontuacaoTotal, dataDaSelecao));

        assertThat(excecaoLancada)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É obrigatório informar a pontuação da família para criar o evento de família selecionada.");
    }

    @Test
    public void naoDeveCriarOEventoCasoNaoSejaInformadaADataDaSelecao() {
        LocalDate dataDaSelecao = null;

        Throwable excecaoLancada = catchThrowable(() -> FamiliaSelecionada.criar(familiaId, quantidadeDeCriteriosAtendidos, pontuacaoTotal, dataDaSelecao));

        assertThat(excecaoLancada)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É obrigatório informar a data da seleção da família para criar o evento de família selecionada.");
    }
}