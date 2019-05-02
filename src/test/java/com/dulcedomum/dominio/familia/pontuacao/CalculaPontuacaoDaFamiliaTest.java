package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculaPontuacaoDaFamiliaTest {

    @Test
    public void deveCalcularAPontuacaoDaFamiliaDeAcordoComMaisDeUmCriterio() {
        Familia familia = FamiliaBuilder.novo().criar();

        Integer pontuacaoEsperadaDoPrimeiroCriterio = 5;
        Integer pontuacaoEsperadaDoSegundoCriterio = 6;

        Integer pontuacaoEsperadaTotal = pontuacaoEsperadaDoPrimeiroCriterio + pontuacaoEsperadaDoSegundoCriterio;

        AvaliadorDeCriterioDePontuacaoDaFamilia avaliadorDoPrimeiroCriterio = mock(AvaliadorDeCriterioDePontuacaoDaFamilia.class);
        when(avaliadorDoPrimeiroCriterio.calcularPontuacaoPeloCriterio(familia)).thenReturn(pontuacaoEsperadaDoPrimeiroCriterio);
        AvaliadorDeCriterioDePontuacaoDaFamilia avaliadorDoSegundoCriterio = mock(AvaliadorDeCriterioDePontuacaoDaFamilia.class);
        when(avaliadorDoSegundoCriterio.calcularPontuacaoPeloCriterio(familia)).thenReturn(pontuacaoEsperadaDoSegundoCriterio);

        List<AvaliadorDeCriterioDePontuacaoDaFamilia> avaliadoresDeCriterios = asList(avaliadorDoPrimeiroCriterio, avaliadorDoSegundoCriterio);
        CalculaPontuacaoDaFamilia calculaPontuacaoDaFamilia = new CalculaPontuacaoDaFamiliaConcreto(avaliadoresDeCriterios);

        Integer pontuacaoObtida = calculaPontuacaoDaFamilia.calcular(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperadaTotal);
    }

    @Test
    public void deveRetornarAQuantidadeDeCriteriosAtendidos() {
        Familia familia = FamiliaBuilder.novo().criar();
        Integer quantidadeDeCriteriosAtendidosEsperada = 2;

        Integer pontuacaoEsperadaDoPrimeiroCriterio = 5;
        Integer pontuacaoEsperadaDoSegundoCriterio = 6;

        AvaliadorDeCriterioDePontuacaoDaFamilia avaliadorDoPrimeiroCriterio = mock(AvaliadorDeCriterioDePontuacaoDaFamilia.class);
        when(avaliadorDoPrimeiroCriterio.calcularPontuacaoPeloCriterio(familia)).thenReturn(pontuacaoEsperadaDoPrimeiroCriterio);
        AvaliadorDeCriterioDePontuacaoDaFamilia avaliadorDoSegundoCriterio = mock(AvaliadorDeCriterioDePontuacaoDaFamilia.class);
        when(avaliadorDoSegundoCriterio.calcularPontuacaoPeloCriterio(familia)).thenReturn(pontuacaoEsperadaDoSegundoCriterio);

        List<AvaliadorDeCriterioDePontuacaoDaFamilia> avaliadoresDeCriterios = asList(avaliadorDoPrimeiroCriterio, avaliadorDoSegundoCriterio);
        CalculaPontuacaoDaFamilia calculaPontuacaoDaFamilia = new CalculaPontuacaoDaFamiliaConcreto(avaliadoresDeCriterios);

        calculaPontuacaoDaFamilia.calcular(familia);

        assertThat(calculaPontuacaoDaFamilia.getQuantidadeDeCriteriosAtendidos()).isEqualTo(quantidadeDeCriteriosAtendidosEsperada);
    }

    @Test
    public void naoDeveContarOsCriteriosQueTiveramPontuacaoZeradaComoAtendidos() {
        Familia familia = FamiliaBuilder.novo().criar();
        Integer quantidadeDeCriteriosAtendidosEsperada = 1;

        Integer pontuacaoDoCriterioAtendido = 5;
        Integer pontuacaoDoCriterioZerada = 0;

        AvaliadorDeCriterioDePontuacaoDaFamilia avaliadorDoCriterioAtendido = mock(AvaliadorDeCriterioDePontuacaoDaFamilia.class);
        when(avaliadorDoCriterioAtendido.calcularPontuacaoPeloCriterio(familia)).thenReturn(pontuacaoDoCriterioAtendido);
        AvaliadorDeCriterioDePontuacaoDaFamilia avaliadorDoCriterioNaoAtendido = mock(AvaliadorDeCriterioDePontuacaoDaFamilia.class);
        when(avaliadorDoCriterioNaoAtendido.calcularPontuacaoPeloCriterio(familia)).thenReturn(pontuacaoDoCriterioZerada);

        List<AvaliadorDeCriterioDePontuacaoDaFamilia> avaliadoresDeCriterios = asList(avaliadorDoCriterioAtendido, avaliadorDoCriterioNaoAtendido);
        CalculaPontuacaoDaFamilia calculaPontuacaoDaFamilia = new CalculaPontuacaoDaFamiliaConcreto(avaliadoresDeCriterios);

        calculaPontuacaoDaFamilia.calcular(familia);

        assertThat(calculaPontuacaoDaFamilia.getQuantidadeDeCriteriosAtendidos()).isEqualTo(quantidadeDeCriteriosAtendidosEsperada);
    }
}
