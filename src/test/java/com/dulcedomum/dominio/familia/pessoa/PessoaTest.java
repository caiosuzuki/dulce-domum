package com.dulcedomum.dominio.familia.pessoa;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PessoaTest {

    private String id;
    private String nome;
    private TipoDePessoa tipo;
    private LocalDate dataDeNascimento;
    private BigDecimal valorDaRenda;

    @Before
    public void setUp() {
        id = UUID.randomUUID().toString();
        nome = "Jonas Borba";
        tipo = TipoDePessoa.DEPENDENTE;
        dataDeNascimento = LocalDate.of(1990, 5, 3);
        valorDaRenda = BigDecimal.valueOf(1200.0);
    }

    @Test
    public void deveCriarUmaPessoa() {
        Pessoa pessoa = Pessoa.criar(nome, tipo, dataDeNascimento, valorDaRenda);

        assertThat(pessoa.getNome()).isEqualTo(nome);
        assertThat(pessoa.getTipo()).isEqualTo(tipo);
        assertThat(pessoa.getDataDeNascimento()).isEqualTo(dataDeNascimento);
        assertThat(pessoa.getValorDaRenda()).isEqualTo(valorDaRenda);
    }

    @Test
    public void naoDeveCriarUmaPessoaComNomeInvalido() {
        String nomeInvalido = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(nomeInvalido, tipo, dataDeNascimento, valorDaRenda));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o nome da pessoa para registrá-la.");
    }

    @Test
    public void naoDeveCriarUmaPessoaComNomeVazio() {
        String nomeVazio = "";

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(nomeVazio, tipo, dataDeNascimento, valorDaRenda));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o nome da pessoa para registrá-la.");
    }

    @Test
    public void naoDeveCriarUmaPessoaSemInformarOTipo() {
        TipoDePessoa tipoInvalido = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(nome, tipoInvalido, dataDeNascimento, valorDaRenda));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o tipo da pessoa para registrá-la.");
    }

    @Test
    public void naoDeveCriarUmaPessoaSemDataDeNascimento() {
        LocalDate dataDeNascimentoInvalida = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(nome, tipo, dataDeNascimentoInvalida, valorDaRenda));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar a data de nascimento da pessoa para registrá-la.");
    }

    @Test
    public void naoDeveCriarUmaPessoaSemInformarOValorDaRenda() {
        BigDecimal valorDaRendaInvalido = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(nome, tipo, dataDeNascimento, valorDaRendaInvalido));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o valor da renda da pessoa para registrá-la.");
    }

    @Test
    public void deveRetornarAIdadeDaPessoa() {
        Pessoa pessoa = PessoaBuilder.novo().comDataDeNascimento(dataDeNascimento).criar();
        int idade = Period.between(dataDeNascimento, LocalDate.now()).getYears();

        int idadeRetornada = pessoa.getIdade();

        assertThat(idadeRetornada).isEqualTo(idade);
    }
}
