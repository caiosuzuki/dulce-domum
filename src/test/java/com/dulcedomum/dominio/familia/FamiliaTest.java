package com.dulcedomum.dominio.familia;

import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.PessoaBuilder;
import com.dulcedomum.dominio.familia.pessoa.TipoDePessoa;
import com.dulcedomum.dominio.familia.pessoa.renda.Renda;
import com.dulcedomum.dominio.familia.pessoa.renda.RendaBuilder;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FamiliaTest {

    private List<Pessoa> pessoasDaFamilia;
    private String familiaId;
    private List<Renda> rendas;
    private StatusDaFamilia status;

    @Before
    public void setUp() {
        familiaId = UUID.randomUUID().toString();
        Pessoa pessoa = PessoaBuilder.novo().criar();
        pessoasDaFamilia = singletonList(pessoa);
        Renda renda = RendaBuilder.novo().criar();
        rendas = singletonList(renda);
        status = StatusDaFamilia.SELECIONADA_EM_OUTRO_PROCESSO_DE_SELECAO;
    }

    @Test
    public void devePossuirUmIdentificador() {
        String familiaId = UUID.randomUUID().toString();

        Familia familia = Familia.criar(familiaId, pessoasDaFamilia, rendas, status);

        assertThat(familia.getId()).isEqualTo(familiaId);
    }

    @Test
    public void deveCriarUmaFamiliaComVariasPessoas() {
        Pessoa pai = PessoaBuilder.novo().criar();
        Pessoa mae = PessoaBuilder.novo().criar();
        List<Pessoa> pessoasDaFamilia = asList(pai, mae);

        Familia familia = Familia.criar(familiaId, pessoasDaFamilia, rendas, status);

        assertThat(familia.getPessoas()).containsExactlyInAnyOrder(pai, mae);
    }

    @Test
    public void devePossuirRendas() {
        Renda rendaEsperada = RendaBuilder.novo().criar();
        List<Renda> rendas = singletonList(rendaEsperada);

        Familia familia = Familia.criar(familiaId, pessoasDaFamilia, rendas, status);
        Renda rendaEncontrada = familia.getRendas().get(0);

        assertThat(familia.getRendas()).hasSize(1);
        assertThat(rendaEncontrada).isEqualTo(rendaEsperada);
    }

    @Test
    public void devePossuirUmStatus() {
        StatusDaFamilia statusEsperado = StatusDaFamilia.JA_POSSUI_UMA_CASA;

        Familia familia = Familia.criar(familiaId, pessoasDaFamilia, rendas, statusEsperado);

        assertThat(familia.getStatus()).isEqualTo(statusEsperado);
    }

    @Test
    public void naoDeveCriarUmaFamiliaSemUmPretendente() {
        Pessoa pessoaQueNaoEhPretendente = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).criar();
        List<Pessoa> pessoasDaFamilia = singletonList(pessoaQueNaoEhPretendente);

        Throwable excecaoLancada = catchThrowable(() -> Familia.criar(familiaId, pessoasDaFamilia, rendas, status));

        assertThat(excecaoLancada)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Não é possível registrar uma família sem um pretendente.");
    }

    @Test
    public void naoDeveCriarUmaFamiliaComMaisDeUmConjuge() {
        Pessoa pretendente = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        Pessoa primeiroConjuge = PessoaBuilder.novo().comTipo(TipoDePessoa.CONJUGE).criar();
        Pessoa segundoConjuge = PessoaBuilder.novo().comTipo(TipoDePessoa.CONJUGE).criar();
        List<Pessoa> pessoasDaFamilia = asList(pretendente, primeiroConjuge, segundoConjuge);

        Throwable excecaoLancada = catchThrowable(() -> Familia.criar(familiaId, pessoasDaFamilia, rendas, status));

        assertThat(excecaoLancada)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Não é possível registrar uma família com mais de um cônjuge.");
    }

    @Test
    public void deveCalcularOValorTotalDaRendaDaFamilia() {
        BigDecimal valorDaPrimeiraRenda = BigDecimal.valueOf(900);
        Renda primeiraRenda = RendaBuilder.novo().comValor(valorDaPrimeiraRenda).criar();
        BigDecimal valorDaSegundaRenda = BigDecimal.valueOf(1200);
        Renda segundaRenda = RendaBuilder.novo().comValor(valorDaSegundaRenda).criar();
        List<Renda> rendas = asList(primeiraRenda, segundaRenda);
        BigDecimal valorTotalDaRendaEsperado = valorDaPrimeiraRenda.add(valorDaSegundaRenda);
        Familia familia = FamiliaBuilder.novo().comRendas(rendas).criar();

        BigDecimal valorTotalDaRendaCalculado = familia.calcularValorTotalDaRenda();

        assertThat(valorTotalDaRendaCalculado).isEqualTo(valorTotalDaRendaEsperado);
    }

    @Test
    public void deveRetornarOPretendenteDaFamilia() {
        Pessoa conjuge = PessoaBuilder.novo().comTipo(TipoDePessoa.CONJUGE).criar();
        Pessoa pretendente = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        Pessoa dependente = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).criar();
        List<Pessoa> pessoasDaFamilia = asList(dependente, conjuge, pretendente);
        Familia familia = FamiliaBuilder.novo().comPessoas(pessoasDaFamilia).criar();

        Pessoa pessoaRetornada = familia.getPretendente().get();

        assertThat(pessoaRetornada).isEqualTo(pretendente);
    }
}
