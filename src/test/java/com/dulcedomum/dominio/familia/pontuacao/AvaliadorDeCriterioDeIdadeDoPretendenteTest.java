package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.PessoaBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class AvaliadorDeCriterioDeIdadeDoPretendenteTest {

    private static final int PONTUACAO_PARA_PRETENDENTE_DE_45_ANOS_OU_MAIS = 3;
    private static final int PONTUACAO_PARA_PRETENDENTE_DE_30_A_44_ANOS = 2;
    private static final int PONTUACAO_PARA_PRETENDENTE_ABAIXO_DE_30_ANOS = 1;
    private static final int QUARENTA_E_CINCO_ANOS = 45;
    private static final int TRINTA_ANOS = 30;

    private AvaliadorDeCriterioDeIdadeDoPretendente avaliadorDeCriterioDeIdadeDoPretendente;

    @Before
    public void setUp() {
        avaliadorDeCriterioDeIdadeDoPretendente = new AvaliadorDeCriterioDeIdadeDoPretendente();
    }

    @Test
    public void devePontuar3PontosQuandoOPretendenteTemMaisDe45Anos() {
        LocalDate dataDeNascimento = LocalDate.now().minusYears(QUARENTA_E_CINCO_ANOS+1);
        Pessoa pretendenteAcimaDe45Anos = PessoaBuilder.novo().comDataDeNascimento(dataDeNascimento).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pretendenteAcimaDe45Anos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeIdadeDoPretendente.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(PONTUACAO_PARA_PRETENDENTE_DE_45_ANOS_OU_MAIS);
    }

    @Test
    public void devePontuar3PontosQuandoOPretendenteTemExatamente45Anos() {
        LocalDate dataDeNascimento = LocalDate.now().minusYears(QUARENTA_E_CINCO_ANOS);
        Pessoa pretendenteDe45Anos = PessoaBuilder.novo().comDataDeNascimento(dataDeNascimento).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pretendenteDe45Anos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeIdadeDoPretendente.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(PONTUACAO_PARA_PRETENDENTE_DE_45_ANOS_OU_MAIS);
    }

    @Test
    public void devePontuar2PontosQuandoOPretendenteTemEntre30E44Anos() {
        LocalDate dataDeNascimento = LocalDate.now().minusYears(TRINTA_ANOS+1);
        Pessoa pretendenteDeIdadeEntre30E44Anos = PessoaBuilder.novo().comDataDeNascimento(dataDeNascimento).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pretendenteDeIdadeEntre30E44Anos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeIdadeDoPretendente.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(PONTUACAO_PARA_PRETENDENTE_DE_30_A_44_ANOS);
    }

    @Test
    public void devePontuar2PontosQuandoOPretendenteTemExatamente30Anos() {
        LocalDate dataDeNascimento = LocalDate.now().minusYears(TRINTA_ANOS);
        Pessoa pretendenteDe30Anos = PessoaBuilder.novo().comDataDeNascimento(dataDeNascimento).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pretendenteDe30Anos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeIdadeDoPretendente.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(PONTUACAO_PARA_PRETENDENTE_DE_30_A_44_ANOS);
    }

    @Test
    public void devePontuar1PontoQuandoOPretendenteTemMenosDe30Anos() {
        LocalDate dataDeNascimento = LocalDate.now().minusYears(TRINTA_ANOS-1);
        Pessoa pretendenteComMenosDe30Anos = PessoaBuilder.novo().comDataDeNascimento(dataDeNascimento).criar();
        Familia familia = FamiliaBuilder.novo().comPessoas(singletonList(pretendenteComMenosDe30Anos)).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeIdadeDoPretendente.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(PONTUACAO_PARA_PRETENDENTE_ABAIXO_DE_30_ANOS);
    }
}
