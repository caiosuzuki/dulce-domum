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
    public void deveTerUmId() {
        String idEsperado = UUID.randomUUID().toString();

        Pessoa pessoa = Pessoa.criar(idEsperado, nome, tipo, dataDeNascimento);

        assertThat(pessoa.id()).isEqualTo(idEsperado);
    }

    @Test
    public void deveTerUmNome() {
        String nomeEsperado = "Sergililsson";

        Pessoa pessoa = Pessoa.criar(id, nomeEsperado, tipo, dataDeNascimento);

        assertThat(pessoa.getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    public void deveTerUmTipo() {
        TipoDePessoa tipoEsperado = TipoDePessoa.PRETENDENTE;

        Pessoa pessoa = Pessoa.criar(id, nome, tipoEsperado, dataDeNascimento);

        assertThat(pessoa.getTipo()).isEqualTo(tipoEsperado);
    }

    @Test
    public void deveTerUmaDataDeNascimento() {
        LocalDate dataDeNascimentoEsperada = LocalDate.of(1997, 3, 20);

        Pessoa pessoa = Pessoa.criar(id, nome, tipo, dataDeNascimentoEsperada);

        assertThat(pessoa.getDataDeNascimento()).isEqualTo(dataDeNascimentoEsperada);
    }

    @Test
    public void naoDeveCriarUmaPessoaComIdentificadorInvalido() {
        String idInvalido = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(idInvalido, nome, tipo, dataDeNascimento));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Não é possível registrar uma pessoa com identificador inválido.");
    }

    @Test
    public void naoDeveCriarUmaPessoaComIdentificadorVazio() {
        String idVazio = "";

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(idVazio, nome, tipo, dataDeNascimento));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Não é possível registrar uma pessoa com identificador vazio.");
    }

    @Test
    public void naoDeveCriarUmaPessoaComNomeInvalido() {
        String nomeInvalido = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(id, nomeInvalido, tipo, dataDeNascimento));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o nome da pessoa para registrá-la.");
    }

    @Test
    public void naoDeveCriarUmaPessoaComNomeVazio() {
        String nomeVazio = "";

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(id, nomeVazio, tipo, dataDeNascimento));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o nome da pessoa para registrá-la.");
    }

    @Test
    public void naoDeveCriarUmaPessoaSemInformarOTipo() {
        TipoDePessoa tipoInvalido = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(id, nome, tipoInvalido, dataDeNascimento));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o tipo da pessoa para registrá-la.");
    }

    @Test
    public void naoDeveCriarUmaPessoaSemDataDeNascimento() {
        LocalDate dataDeNascimentoInvalida = null;

        Throwable excecaoLancada = catchThrowable(() -> Pessoa.criar(id, nome, tipo, dataDeNascimentoInvalida));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar a data de nascimento da pessoa para registrá-la.");
    }

    @Test
    public void deveRetornarAIdadeDaPessoa() {
        Pessoa pessoa = PessoaBuilder.novo().comDataDeNascimento(dataDeNascimento).criar();
        int idadeEsperada = Period.between(dataDeNascimento, LocalDate.now()).getYears();

        int idadeRetornada = pessoa.getIdade();

        assertThat(idadeRetornada).isEqualTo(idadeEsperada);
    }
}
