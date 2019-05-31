package com.dulcedomum.dominio.familia;

import com.dulcedomum.dominio.familia.eventodedominio.FamiliaSelecionada;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.PessoaBuilder;
import com.dulcedomum.dominio.familia.pessoa.TipoDePessoa;
import com.dulcedomum.dominio.familia.selecao.DadosDaSelecaoDaFamilia;
import com.dulcedomum.dominio.familia.selecao.DadosDaSelecaoDaFamiliaBuilder;
import com.dulcedomum.eventodedominiobase.NotificadorDeEventoDeDominio;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class FamiliaTest {

    private List<Pessoa> pessoasDaFamilia;
    private String familiaId;
    private StatusDaFamilia status;

    @Before
    public void setUp() {
        familiaId = UUID.randomUUID().toString();
        Pessoa pessoa = PessoaBuilder.novo().criar();
        pessoasDaFamilia = singletonList(pessoa);
        status = StatusDaFamilia.SELECIONADA_EM_OUTRO_PROCESSO_DE_SELECAO;
    }

    @Test
    public void devePossuirUmIdentificador() {
        String familiaId = UUID.randomUUID().toString();

        Familia familia = Familia.criar(familiaId, pessoasDaFamilia, status);

        assertThat(familia.getId()).isEqualTo(familiaId);
    }

    @Test
    public void deveCriarUmaFamiliaComVariasPessoas() {
        Pessoa pai = PessoaBuilder.novo().criar();
        Pessoa mae = PessoaBuilder.novo().criar();
        List<Pessoa> pessoasDaFamilia = asList(pai, mae);

        Familia familia = Familia.criar(familiaId, pessoasDaFamilia, status);

        assertThat(familia.getPessoas()).containsExactlyInAnyOrder(pai, mae);
    }

    @Test
    public void devePossuirUmStatus() {
        StatusDaFamilia statusEsperado = StatusDaFamilia.JA_POSSUI_UMA_CASA;

        Familia familia = Familia.criar(familiaId, pessoasDaFamilia, statusEsperado);

        assertThat(familia.getStatus()).isEqualTo(statusEsperado);
    }

    @Test
    public void naoDeveCriarUmaFamiliaSemUmPretendente() {
        Pessoa pessoaQueNaoEhPretendente = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).criar();
        List<Pessoa> pessoasDaFamilia = singletonList(pessoaQueNaoEhPretendente);

        Throwable excecaoLancada = catchThrowable(() -> Familia.criar(familiaId, pessoasDaFamilia, status));

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

        Throwable excecaoLancada = catchThrowable(() -> Familia.criar(familiaId, pessoasDaFamilia, status));

        assertThat(excecaoLancada)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Não é possível registrar uma família com mais de um cônjuge.");
    }

    @Test
    public void deveCalcularOValorTotalDaRendaDaFamilia() {
        BigDecimal valorDaPrimeiraRenda = BigDecimal.valueOf(900);
        Pessoa primeiraPessoa = PessoaBuilder.novo().comValorDaRenda(valorDaPrimeiraRenda).criar();
        BigDecimal valorDaSegundaRenda = BigDecimal.valueOf(1200);
        Pessoa segundaPessoa = PessoaBuilder.novo().comValorDaRenda(valorDaSegundaRenda).criar();
        BigDecimal valorTotalDaRendaEsperado = valorDaPrimeiraRenda.add(valorDaSegundaRenda);
        Familia familia = FamiliaBuilder.novo().comPessoas(asList(primeiraPessoa, segundaPessoa)).criar();

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

    @Test
    public void deveRetornarOsDependentesDaFamilia() {
        Pessoa conjuge = PessoaBuilder.novo().comTipo(TipoDePessoa.CONJUGE).criar();
        Pessoa pretendente = PessoaBuilder.novo().comTipo(TipoDePessoa.PRETENDENTE).criar();
        Pessoa dependente = PessoaBuilder.novo().comTipo(TipoDePessoa.DEPENDENTE).criar();
        List<Pessoa> pessoasDaFamilia = asList(dependente, conjuge, pretendente);
        Familia familia = FamiliaBuilder.novo().comPessoas(pessoasDaFamilia).criar();

        List<Pessoa> pessoasRetornadas = familia.getDependentes();

        assertThat(pessoasRetornadas.get(0)).isEqualTo(dependente);
    }

    @Test
    public void deveNotificarOEventoDeDominioFamiliaSelecionadaAoSelecionarAFamilia() {
        NotificadorDeEventoDeDominio notificadorDeEventoDeDominio = mock(NotificadorDeEventoDeDominio.class);
        NotificadorDeEventoDeDominio.setNotificadorDeEventoDeDominioCorrente(notificadorDeEventoDeDominio);
        ArgumentCaptor<FamiliaSelecionada> capturadorDeArgumento = ArgumentCaptor.forClass(FamiliaSelecionada.class);
        doNothing().when(notificadorDeEventoDeDominio).notificarSobre(capturadorDeArgumento.capture());
        Familia familia = FamiliaBuilder.novo().criar();
        DadosDaSelecaoDaFamilia dadosDaSelecaoDaFamilia = DadosDaSelecaoDaFamiliaBuilder.novo().criar();

        familia.selecionar(dadosDaSelecaoDaFamilia);

        FamiliaSelecionada eventoCapturado = capturadorDeArgumento.getValue();
        assertThat(eventoCapturado.getFamiliaId()).isEqualTo(familia.getId());
        assertThat(eventoCapturado.getQuantidadeDeCriteriosAtendidos()).isEqualTo(dadosDaSelecaoDaFamilia.getQuantidadeDeCriteriosAtendidos());
        assertThat(eventoCapturado.getPontuacaoTotal()).isEqualTo(dadosDaSelecaoDaFamilia.getPontuacao());
        assertThat(eventoCapturado.getDataDaSelecao()).isEqualTo(dadosDaSelecaoDaFamilia.getDataDaSelecao());
    }
}
