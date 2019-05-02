package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.PessoaBuilder;
import com.dulcedomum.dominio.familia.pessoa.TipoDePessoa;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class AvaliadorDeCriterioDeQuantidadeDeDependentesTest {

    private static final Integer DEZOITO_ANOS = 18;
    private static final Integer TRES_PONTOS = 3;
    private static final Integer DOIS_PONTOS = 2;
    private static final Integer ZERO_PONTOS = 0;

    private AvaliadorDeCriterioDeQuantidadeDeDependentes avaliadorDeCriterioDeQuantidadeDeDependentes;

    @Before
    public void setUp() {
        avaliadorDeCriterioDeQuantidadeDeDependentes = new AvaliadorDeCriterioDeQuantidadeDeDependentes();
    }

    @Test
    public void devePontuar3PontosCasoAFamiliaTenha3OuMaisDependentes() {
        Pessoa pretendente = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        Pessoa primeiroDependente = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(DEZOITO_ANOS-1).criar();
        Pessoa segundoDependente = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(DEZOITO_ANOS-2).criar();
        Pessoa terceiroDependente = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(DEZOITO_ANOS-3).criar();
        List<Pessoa> pessoasDaFamilia = asList(pretendente, primeiroDependente, segundoDependente, terceiroDependente);
        Familia familia = FamiliaBuilder.novo().comPessoas(pessoasDaFamilia).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeQuantidadeDeDependentes.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(TRES_PONTOS);
    }

    @Test
    public void devePontuar2PontosCasoAFamiliaTenhaEntre1E2Dependentes() {
        Pessoa pretendente = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        Pessoa primeiroDependente = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(DEZOITO_ANOS-2).criar();
        Pessoa segundoDependente = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(DEZOITO_ANOS-3).criar();
        List<Pessoa> pessoasDaFamilia = asList(pretendente, primeiroDependente, segundoDependente);
        Familia familia = FamiliaBuilder.novo().comPessoas(pessoasDaFamilia).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeQuantidadeDeDependentes.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(DOIS_PONTOS);
    }

    @Test
    public void devePontuar0PontosCasoAFamiliaNaoTenhaDependentes() {
        Pessoa pretendente = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        List<Pessoa> pessoasDaFamilia = singletonList(pretendente);
        Familia familia = FamiliaBuilder.novo().comPessoas(pessoasDaFamilia).criar();

        Integer pontuacaoObtida = avaliadorDeCriterioDeQuantidadeDeDependentes.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoObtida).isEqualTo(ZERO_PONTOS);
    }

    @Test
    public void naoDeveLevarEmConsideracaoDependentesMaioresDe18Anos() {
        Pessoa dependenteDe18Anos = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(DEZOITO_ANOS).criar();
        Pessoa pretendente = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        List<Pessoa> pessoasDaFamilia = asList(dependenteDe18Anos, pretendente);
        Familia familia = FamiliaBuilder.novo().comPessoas(pessoasDaFamilia).criar();

        Integer pontuacaoDaFamiliaComDependenteDe18Anos = avaliadorDeCriterioDeQuantidadeDeDependentes.calcularPontuacaoPeloCriterio(familia);

        assertThat(pontuacaoDaFamiliaComDependenteDe18Anos).isEqualTo(ZERO_PONTOS);
    }
}
