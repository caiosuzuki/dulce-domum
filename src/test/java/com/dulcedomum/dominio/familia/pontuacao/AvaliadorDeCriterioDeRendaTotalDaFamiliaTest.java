package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import com.dulcedomum.dominio.familia.pessoa.renda.Renda;
import com.dulcedomum.dominio.familia.pessoa.renda.RendaBuilder;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class AvaliadorDeCriterioDeRendaTotalDaFamiliaTest {

    private static final int VALOR_LIMITE_PARA_PONTUAR_5_PONTOS = 900;
    private static final int VALOR_LIMITE_PARA_PONTUAR_3_PONTOS = 1500;
    private static final int VALOR_LIMITE_PARA_PONTUAR_1_PONTO = 2000;
    private static final int CINCO_PONTOS = 5;
    private static final int TRES_PONTOS = 3;
    private static final int UM_PONTO = 1;
    private static final int ZERO_PONTOS = 0;

    private AvaliadorDeCriterioDeRendaTotalDaFamilia avaliadorDeCriterioDeRendaTotalDaFamilia;

    @Before
    public void setUp() {
        avaliadorDeCriterioDeRendaTotalDaFamilia = new AvaliadorDeCriterioDeRendaTotalDaFamilia();
    }

    @Test
    public void devePontuar5PontosCasoARendaTotalDaFamiliaSejaInferiorAoValorLimiteParaACotaDe5Pontos() {
        int pontuacaoEsperada = CINCO_PONTOS;
        Renda rendaInferiorAoValorLimiteDaCotaDe5Pontos = RendaBuilder.novo()
                .comValor(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_5_PONTOS - 1)).criar();
        Familia familia = FamiliaBuilder.novo().comRendas(singletonList(rendaInferiorAoValorLimiteDaCotaDe5Pontos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar5PontosCasoARendaTotalDaFamiliaSejaExatamenteOValorLimiteDaCotaDe5Pontos() {
        int pontuacaoEsperada = CINCO_PONTOS;
        Renda rendaExatamenteNoLimiteDaCotaDe5Pontos = RendaBuilder.novo().comValor(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_5_PONTOS)).criar();
        Familia familia = FamiliaBuilder.novo().comRendas(singletonList(rendaExatamenteNoLimiteDaCotaDe5Pontos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar3PontosCasoARendaTotalDaFamiliaFor1RealAcimaDoValorLimiteDaCotaDe5Pontos() {
        int pontuacaoEsperada = TRES_PONTOS;
        Renda renda1RealAcimaDoLimiteDaCotaDe5Pontos = RendaBuilder.novo()
                .comValor(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_5_PONTOS + 1)).criar();
        Familia familia = FamiliaBuilder.novo().comRendas(singletonList(renda1RealAcimaDoLimiteDaCotaDe5Pontos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar3PontosCasoARendaTotalDaFamiliaSejaInferiorAoValorLimiteDaCotaDe3Pontos() {
        int pontuacaoEsperada = TRES_PONTOS;
        Renda rendaInferiorAoValorLimiteDaCotaDe3Pontos = RendaBuilder.novo().comValor(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_3_PONTOS - 1)).criar();
        Familia familia = FamiliaBuilder.novo().comRendas(singletonList(rendaInferiorAoValorLimiteDaCotaDe3Pontos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar3PontosCasoARendaTotalDaFamiliaSejaExatamenteOValorLimiteDaCotaDe3Pontos() {
        int pontuacaoEsperada = TRES_PONTOS;
        Renda rendaNoLimiteDaCotaDe3Pontos = RendaBuilder.novo().comValor(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_3_PONTOS)).criar();
        Familia familia = FamiliaBuilder.novo().comRendas(singletonList(rendaNoLimiteDaCotaDe3Pontos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar1PontoCasoARendaSeja1RealSuperiorACotaDe3Pontos() {
        int pontuacaoEsperada = UM_PONTO;
        Renda renda1RealSuperiorAoValorLimiteDaCotaDe3Pontos = RendaBuilder.novo().comValor(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_3_PONTOS + 1)).criar();
        Familia familia = FamiliaBuilder.novo().comRendas(singletonList(renda1RealSuperiorAoValorLimiteDaCotaDe3Pontos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar1PontoCasoARendaSejaInferiorAoValorLimiteDaCotaDe1Ponto() {
        int pontuacaoEsperada = UM_PONTO;
        Renda rendaInferiorAoValorLimiteDaCotaDe1Ponto = RendaBuilder.novo().comValor(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_1_PONTO - 1)).criar();
        Familia familia = FamiliaBuilder.novo().comRendas(singletonList(rendaInferiorAoValorLimiteDaCotaDe1Ponto)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar1PontoCasoARendaEstejaNoValorLimiteDaCotaDe1Ponto() {
        int pontuacaoEsperada = UM_PONTO;
        Renda rendaNoLimiteParaDaCotaDe1Ponto = RendaBuilder.novo().comValor(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_1_PONTO)).criar();
        Familia familia = FamiliaBuilder.novo().comRendas(singletonList(rendaNoLimiteParaDaCotaDe1Ponto)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar0PontosCasoARendaSejaMaiorDoQueOValorLimiteDaCotaDe1Ponto() {
        int pontuacaoEsperada = ZERO_PONTOS;
        Renda rendaMaiorQueOLimiteDaCotaDe1Ponto = RendaBuilder.novo().comValor(BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_1_PONTO + 1)).criar();
        Familia familia = FamiliaBuilder.novo().comRendas(singletonList(rendaMaiorQueOLimiteDaCotaDe1Ponto)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }
}
