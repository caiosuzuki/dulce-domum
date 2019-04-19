package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculaPontuacaoDaFamiliaTest {

    @Test
    public void deveCalcularAPontuacaoDaFamiliaDeAcordoComMaisDeUmCriterio() {
        Familia familia = FamiliaBuilder.novo().criar();
        int pontuacaoEsperadaDoPrimeiroCriterio = 5;
        int pontuacaoEsperadaPeloSegundoCriterio = 6;
        int pontuacaoEsperadaTotal = pontuacaoEsperadaDoPrimeiroCriterio + pontuacaoEsperadaPeloSegundoCriterio;
        AvaliadorDeCriterioDePontuacaoDaFamilia avaliadorDoPrimeiroCriterio = mock(AvaliadorDeCriterioDePontuacaoDaFamilia.class);
        when(avaliadorDoPrimeiroCriterio.calcularPontuacaoPeloCriterio(familia)).thenReturn(pontuacaoEsperadaDoPrimeiroCriterio);
        AvaliadorDeCriterioDePontuacaoDaFamilia avaliadorDoSegundoCriterio = mock(AvaliadorDeCriterioDePontuacaoDaFamilia.class);
        when(avaliadorDoSegundoCriterio.calcularPontuacaoPeloCriterio(familia)).thenReturn(pontuacaoEsperadaPeloSegundoCriterio);
        List<AvaliadorDeCriterioDePontuacaoDaFamilia> avaliadoresDeCriterios = Arrays.asList(avaliadorDoPrimeiroCriterio, avaliadorDoSegundoCriterio);
        CalculaPontuacaoDaFamilia calculaPontuacaoDaFamilia = new CalculaPontuacaoDaFamiliaConcreto(avaliadoresDeCriterios);

        int pontuacaoObtida = calculaPontuacaoDaFamilia.calcular(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperadaTotal);
    }
}
