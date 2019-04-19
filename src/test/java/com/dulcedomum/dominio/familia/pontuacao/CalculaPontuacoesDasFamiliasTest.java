package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculaPontuacoesDasFamiliasTest {
    @Test
    public void deveCalcularAPontuacaoDeUmaFamiliaBaseadaEmUmCriterio() {
        Familia familia = FamiliaBuilder.novo().criar();
        List<Familia> familias = singletonList(familia);

        Integer pontuacaoEsperada = 5;
        Map<String, Integer> mapaDePontuacaoDoCriterio = new HashMap<>();
        mapaDePontuacaoDoCriterio.put(familia.getId(), pontuacaoEsperada);

        AvaliadorDeCriterioDePontuacoesDasFamilias avaliadorDoCriterio = mock(AvaliadorDeCriterioDePontuacoesDasFamilias.class);
        when(avaliadorDoCriterio.calcularPontuacoesPeloCriterio(familias)).thenReturn(mapaDePontuacaoDoCriterio);

        List<AvaliadorDeCriterioDePontuacoesDasFamilias> avaliadoresDeCriterios = singletonList(avaliadorDoCriterio);
        CalculaPontuacoesDasFamilias calculaPontuacoesDasFamilias = new CalculaPontuacoesDasFamiliasConcreto(avaliadoresDeCriterios);

        Map<String, Integer> mapaDeFamiliasIdEPontuacoes = calculaPontuacoesDasFamilias.calcular(familias);
        Integer pontuacaoObtida = mapaDeFamiliasIdEPontuacoes.get(familia.getId());

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void deveCalcularAPontuacaoDaFamiliaDeAcordoComMaisDeUmCriterio() {
        Familia familia = FamiliaBuilder.novo().criar();
        List<Familia> familias = singletonList(familia);

        Integer pontuacaoEsperadaDoPrimeiroCriterio = 5;
        Map<String, Integer> mapaDePontuacaoDoPrimeiroCriterio = new HashMap<>();
        mapaDePontuacaoDoPrimeiroCriterio.put(familia.getId(), pontuacaoEsperadaDoPrimeiroCriterio);
        Integer pontuacaoEsperadaDoSegundoCriterio = 6;
        Map<String, Integer> mapaDePontuacaoDoSegundoCriterio = new HashMap<>();
        mapaDePontuacaoDoSegundoCriterio.put(familia.getId(), pontuacaoEsperadaDoSegundoCriterio);

        Integer pontuacaoEsperadaTotal = pontuacaoEsperadaDoPrimeiroCriterio + pontuacaoEsperadaDoSegundoCriterio;

        AvaliadorDeCriterioDePontuacoesDasFamilias avaliadorDoPrimeiroCriterio = mock(AvaliadorDeCriterioDePontuacoesDasFamilias.class);
        when(avaliadorDoPrimeiroCriterio.calcularPontuacoesPeloCriterio(familias)).thenReturn(mapaDePontuacaoDoPrimeiroCriterio);
        AvaliadorDeCriterioDePontuacoesDasFamilias avaliadorDoSegundoCriterio = mock(AvaliadorDeCriterioDePontuacoesDasFamilias.class);
        when(avaliadorDoSegundoCriterio.calcularPontuacoesPeloCriterio(familias)).thenReturn(mapaDePontuacaoDoSegundoCriterio);

        List<AvaliadorDeCriterioDePontuacoesDasFamilias> avaliadoresDeCriterios = asList(avaliadorDoPrimeiroCriterio, avaliadorDoSegundoCriterio);
        CalculaPontuacoesDasFamilias calculaPontuacoesDasFamilias = new CalculaPontuacoesDasFamiliasConcreto(avaliadoresDeCriterios);

        Map<String, Integer> mapaDeFamiliasIdEPontuacoes = calculaPontuacoesDasFamilias.calcular(familias);
        Integer pontuacaoObtida = mapaDeFamiliasIdEPontuacoes.get(familia.getId());

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperadaTotal);
    }

    @Test
    public void deveCalcularAPontuacaoDeMaisDeUmaFamilia() {
        Familia primeiraFamilia = FamiliaBuilder.novo().criar();
        Familia segundaFamilia = FamiliaBuilder.novo().criar();
        List<Familia> familias = asList(primeiraFamilia, segundaFamilia);

        Map<String, Integer> mapaDePontuacaoDoCriterio = new HashMap<>();
        Integer pontuacaoEsperadaDaPrimeiraFamilia = 5;
        mapaDePontuacaoDoCriterio.put(primeiraFamilia.getId(), pontuacaoEsperadaDaPrimeiraFamilia);
        Integer pontuacaoEsperadaDaSegundaFamilia = 7;
        mapaDePontuacaoDoCriterio.put(segundaFamilia.getId(), pontuacaoEsperadaDaSegundaFamilia);

        AvaliadorDeCriterioDePontuacoesDasFamilias avaliadorDoCriterio = mock(AvaliadorDeCriterioDePontuacoesDasFamilias.class);
        when(avaliadorDoCriterio.calcularPontuacoesPeloCriterio(familias)).thenReturn(mapaDePontuacaoDoCriterio);

        List<AvaliadorDeCriterioDePontuacoesDasFamilias> avaliadoresDeCriterios = singletonList(avaliadorDoCriterio);
        CalculaPontuacoesDasFamilias calculaPontuacoesDasFamilias = new CalculaPontuacoesDasFamiliasConcreto(avaliadoresDeCriterios);

        Map<String, Integer> mapaDeFamiliasIdEPontuacoes = calculaPontuacoesDasFamilias.calcular(familias);
        Integer pontuacaoObtidaDaPrimeiraFamilia = mapaDeFamiliasIdEPontuacoes.get(primeiraFamilia.getId());
        Integer pontuacaoObtidaDaSegundaFamilia = mapaDeFamiliasIdEPontuacoes.get(segundaFamilia.getId());

        assertThat(pontuacaoObtidaDaPrimeiraFamilia).isEqualTo(pontuacaoEsperadaDaPrimeiraFamilia);
        assertThat(pontuacaoObtidaDaSegundaFamilia).isEqualTo(pontuacaoEsperadaDaSegundaFamilia);
    }
}
