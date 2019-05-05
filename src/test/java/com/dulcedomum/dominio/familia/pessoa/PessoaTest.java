package com.dulcedomum.dominio.familia.pessoa;

import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void setUp() {
        id = UUID.randomUUID().toString();
        nome = "Jonas Borba";
        tipo = TipoDePessoa.DEPENDENTE;
        dataDeNascimento = LocalDate.of(1990, 5, 3);
    }

    @Test
    public void deveTerUmNome() {
        String nome = "Sergililsson";

        Pessoa pessoa = Pessoa.criar(nome, tipo, dataDeNascimento);

        assertThat(pessoa.getNome()).isEqualTo(nome);
    }

    @Test
    public void deveTerUmTipo() {
        TipoDePessoa tipo = TipoDePessoa.PRETENDENTE;

        Pessoa pessoa = Pessoa.criar(nome, tipo, dataDeNascimento);

        assertThat(pessoa.getTipo()).isEqualTo(tipo);
    }

    @Test
    public void deveTerUmaDataDeNascimento() {
        LocalDate dataDeNascimento = LocalDate.of(1997, 3, 20);

        Pessoa pessoa = Pessoa.criar(nome, tipo, dataDeNascimento);

        assertThat(pessoa.getDataDeNascimento()).isEqualTo(dataDeNascimento);
    }

    @Test
    public void naoDeveCriarUmaPessoaComNomeInvalido() {
        String nomeInvalido = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(nomeInvalido, tipo, dataDeNascimento));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o nome da pessoa para registrá-la.");
    }

    @Test
    public void naoDeveCriarUmaPessoaComNomeVazio() {
        String nomeVazio = "";

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(nomeVazio, tipo, dataDeNascimento));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o nome da pessoa para registrá-la.");
    }

    @Test
    public void naoDeveCriarUmaPessoaSemInformarOTipo() {
        TipoDePessoa tipoInvalido = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(nome, tipoInvalido, dataDeNascimento));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o tipo da pessoa para registrá-la.");
    }

    @Test
    public void naoDeveCriarUmaPessoaSemDataDeNascimento() {
        LocalDate dataDeNascimentoInvalida = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(nome, tipo, dataDeNascimentoInvalida));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar a data de nascimento da pessoa para registrá-la.");
    }

    @Test
    public void deveRetornarAIdadeDaPessoa() {
        Pessoa pessoa = PessoaBuilder.novo().comDataDeNascimento(dataDeNascimento).criar();
        int idade = Period.between(dataDeNascimento, LocalDate.now()).getYears();

        int idadeRetornada = pessoa.getIdade();

        assertThat(idadeRetornada).isEqualTo(idade);
    }
}
