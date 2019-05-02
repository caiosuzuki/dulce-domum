package com.dulcedomum.dominio.familia.selecao;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class DadosDaSelecaoDaFamiliaTest {


    private Integer quantidadeDeCriteriosAtendidosEsperada;
    private Integer pontuacaoEsperada;
    private LocalDate dataDaSelecaoEsperada;

    @Before
    public void setUp() {
        quantidadeDeCriteriosAtendidosEsperada = 3;
        pontuacaoEsperada = 8;
        dataDaSelecaoEsperada = LocalDate.now();
    }

    @Test
    public void deveCriarDadosDaSelecaoDaFamilia() {
        DadosDaSelecaoDaFamilia dadosDaSelecaoDaFamilia = DadosDaSelecaoDaFamilia.criar(quantidadeDeCriteriosAtendidosEsperada, pontuacaoEsperada);

        assertThat(dadosDaSelecaoDaFamilia.getQuantidadeDeCriteriosAtendidos()).isEqualTo(quantidadeDeCriteriosAtendidosEsperada);
        assertThat(dadosDaSelecaoDaFamilia.getPontuacao()).isEqualTo(pontuacaoEsperada);
        assertThat(dadosDaSelecaoDaFamilia.getDataDaSelecao()).isEqualTo(dataDaSelecaoEsperada);
    }

    @Test
    public void naoDeveCriarDadosDaSelecaoDaFamiliaSemQuantidadeDeCriteriosAtendidos() {
        Throwable excecaoLancada = catchThrowable(() -> DadosDaSelecaoDaFamilia.criar(null, pontuacaoEsperada));

        assertThat(excecaoLancada)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Não é possível criar os dados da seleção da família sem a quantidade de critérios atendidos.");
    }

    @Test
    public void naoDeveCriarDadosDaSelecaoDaFamiliaSemAPontuacao() {
        Throwable excecaoLancada = catchThrowable(() -> DadosDaSelecaoDaFamilia.criar(quantidadeDeCriteriosAtendidosEsperada, null));

        assertThat(excecaoLancada)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Não é possível criar os dados da seleção da família sem a pontuação.");
    }
}
