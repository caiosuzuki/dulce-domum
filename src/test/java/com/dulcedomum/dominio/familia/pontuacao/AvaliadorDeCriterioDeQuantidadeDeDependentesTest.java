package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.PessoaBuilder;
import com.dulcedomum.dominio.familia.pessoa.TipoDePessoa;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class AvaliadorDeCriterioDeQuantidadeDeDependentesTest {

    private static final Integer PONTUACAO_DE_FAVORECIDO = 3;
    private static final Integer PONTUACAO_ZERADA = 0;
    private static final Integer DEZOITO_ANOS = 18;


    private AvaliadorDeCriterioDeQuantidadeDeDependentes avaliadorDeCriterioDeQuantidadeDeDependentes;

    @Before
    public void setUp() {
        avaliadorDeCriterioDeQuantidadeDeDependentes = new AvaliadorDeCriterioDeQuantidadeDeDependentes();
    }

    @Test
    public void devePontuarComoFavorecidoApenasAFamiliaComMaiorNumeroDeDependentes() {
        Pessoa pretendenteDaPrimeiraFamilia = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        Pessoa dependenteDaPrimeiraFamilia = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(14).criar();
        List<Pessoa> pessoasDaPrimeiraFamilia = asList(pretendenteDaPrimeiraFamilia, dependenteDaPrimeiraFamilia);
        Familia primeiraFamilia = FamiliaBuilder.novo().comPessoas(pessoasDaPrimeiraFamilia).criar();

        Pessoa pretendenteDaSegundaFamilia = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        Pessoa primeiroDependenteDaSegundaFamilia = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(16).criar();
        Pessoa segundoDependenteDaSegundaFamilia = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(15).criar();
        List<Pessoa> pessoasDaSegundaFamilia = asList(primeiroDependenteDaSegundaFamilia, segundoDependenteDaSegundaFamilia, pretendenteDaSegundaFamilia);
        Familia segundaFamilia = FamiliaBuilder.novo().comPessoas(pessoasDaSegundaFamilia).criar();

        List<Familia> familias = asList(primeiraFamilia, segundaFamilia);

        Map<String, Integer> mapaDeFamiliaIdsEPontuacoes = avaliadorDeCriterioDeQuantidadeDeDependentes.calcularPontuacoesPeloCriterio(familias);
        Integer pontuacaoDaPrimeiraFamilia = mapaDeFamiliaIdsEPontuacoes.get(primeiraFamilia.getId());
        Integer pontuacaoDaSegundaFamilia = mapaDeFamiliaIdsEPontuacoes.get(segundaFamilia.getId());

        assertThat(pontuacaoDaSegundaFamilia).isEqualTo(PONTUACAO_DE_FAVORECIDO);
        assertThat(pontuacaoDaPrimeiraFamilia).isEqualTo(PONTUACAO_ZERADA);
    }

    @Test
    public void naoDeveLevarEmConsideracaoDependentesMaioresDe18Anos() {
        Pessoa dependenteDe18Anos = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(DEZOITO_ANOS).criar();
        Pessoa pretendenteDaFamiliaComDependenteDe18Anos = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        List<Pessoa> pessoasDaFamiliaComDependenteDe18Anos = asList(dependenteDe18Anos, pretendenteDaFamiliaComDependenteDe18Anos);
        Familia familiaComDependenteDe18Anos = FamiliaBuilder.novo().comPessoas(pessoasDaFamiliaComDependenteDe18Anos).criar();

        Pessoa pretendenteDaFamiliaComMenorDe18 = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        Pessoa dependenteMenorDe18Anos = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).comIdade(DEZOITO_ANOS - 1).criar();
        List<Pessoa> pessoasDaFamiliaComMenorDe18 = asList(dependenteMenorDe18Anos, pretendenteDaFamiliaComMenorDe18);
        Familia familiaComDependenteMenorDe18 = FamiliaBuilder.novo().comPessoas(pessoasDaFamiliaComMenorDe18).criar();

        List<Familia> familias = asList(familiaComDependenteDe18Anos, familiaComDependenteMenorDe18);

        Map<String, Integer> mapaDeFamiliaIdsEPontuacoes = avaliadorDeCriterioDeQuantidadeDeDependentes.calcularPontuacoesPeloCriterio(familias);
        Integer pontuacaoDaFamiliaComDependenteDe18Anos = mapaDeFamiliaIdsEPontuacoes.get(familiaComDependenteDe18Anos.getId());
        Integer pontuacaoDaFamiliaComDependenteMenorDe18 = mapaDeFamiliaIdsEPontuacoes.get(familiaComDependenteMenorDe18.getId());

        assertThat(pontuacaoDaFamiliaComDependenteDe18Anos).isEqualTo(PONTUACAO_ZERADA);
        assertThat(pontuacaoDaFamiliaComDependenteMenorDe18).isEqualTo(PONTUACAO_DE_FAVORECIDO);
    }
}
