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
}
