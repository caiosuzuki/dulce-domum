package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.PessoaBuilder;
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
        BigDecimal rendaInferiorAoValorLimiteDaCotaDe5Pontos = BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_5_PONTOS - 1);
        Pessoa pessoa = PessoaBuilder.novo().comValorDaRenda(rendaInferiorAoValorLimiteDaCotaDe5Pontos).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pessoa)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar5PontosCasoARendaTotalDaFamiliaSejaExatamenteOValorLimiteDaCotaDe5Pontos() {
        int pontuacaoEsperada = CINCO_PONTOS;
        BigDecimal rendaExatamenteNoLimiteDaCotaDe5Pontos = BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_5_PONTOS);
        Pessoa pessoa = PessoaBuilder.novo().comValorDaRenda(rendaExatamenteNoLimiteDaCotaDe5Pontos).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pessoa)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar3PontosCasoARendaTotalDaFamiliaFor1RealAcimaDoValorLimiteDaCotaDe5Pontos() {
        int pontuacaoEsperada = TRES_PONTOS;
        BigDecimal renda1RealAcimaDoLimiteDaCotaDe5Pontos = BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_5_PONTOS + 1);
        Pessoa pessoa = PessoaBuilder.novo().comValorDaRenda(renda1RealAcimaDoLimiteDaCotaDe5Pontos).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pessoa)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar3PontosCasoARendaTotalDaFamiliaSejaInferiorAoValorLimiteDaCotaDe3Pontos() {
        int pontuacaoEsperada = TRES_PONTOS;
        BigDecimal rendaInferiorAoValorLimiteDaCotaDe3Pontos = BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_3_PONTOS - 1);
        Pessoa pessoa = PessoaBuilder.novo().comValorDaRenda(rendaInferiorAoValorLimiteDaCotaDe3Pontos).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pessoa)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar3PontosCasoARendaTotalDaFamiliaSejaExatamenteOValorLimiteDaCotaDe3Pontos() {
        int pontuacaoEsperada = TRES_PONTOS;
        BigDecimal rendaNoLimiteDaCotaDe3Pontos = BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_3_PONTOS);
        Pessoa pessoa = PessoaBuilder.novo().comValorDaRenda(rendaNoLimiteDaCotaDe3Pontos).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pessoa)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar1PontoCasoARendaSeja1RealSuperiorACotaDe3Pontos() {
        int pontuacaoEsperada = UM_PONTO;
        BigDecimal renda1RealSuperiorAoValorLimiteDaCotaDe3Pontos = BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_3_PONTOS + 1);
        Pessoa pessoa = PessoaBuilder.novo().comValorDaRenda(renda1RealSuperiorAoValorLimiteDaCotaDe3Pontos).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pessoa)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar1PontoCasoARendaSejaInferiorAoValorLimiteDaCotaDe1Ponto() {
        int pontuacaoEsperada = UM_PONTO;
        BigDecimal rendaInferiorAoValorLimiteDaCotaDe1Ponto = BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_1_PONTO - 1);
        Pessoa pessoa = PessoaBuilder.novo().comValorDaRenda(rendaInferiorAoValorLimiteDaCotaDe1Ponto).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pessoa)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar1PontoCasoARendaEstejaNoValorLimiteDaCotaDe1Ponto() {
        int pontuacaoEsperada = UM_PONTO;
        BigDecimal rendaNoLimiteParaDaCotaDe1Ponto = BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_1_PONTO);
        Pessoa pessoa = PessoaBuilder.novo().comValorDaRenda(rendaNoLimiteParaDaCotaDe1Ponto).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pessoa)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }

    @Test
    public void devePontuar0PontosCasoARendaSejaMaiorDoQueOValorLimiteDaCotaDe1Ponto() {
        int pontuacaoEsperada = ZERO_PONTOS;
        BigDecimal rendaMaiorQueOLimiteDaCotaDe1Ponto = BigDecimal.valueOf(VALOR_LIMITE_PARA_PONTUAR_1_PONTO + 1);
        Pessoa pessoa = PessoaBuilder.novo().comValorDaRenda(rendaMaiorQueOLimiteDaCotaDe1Ponto).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pessoa)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeRendaTotalDaFamilia.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(pontuacaoEsperada);
    }
}
