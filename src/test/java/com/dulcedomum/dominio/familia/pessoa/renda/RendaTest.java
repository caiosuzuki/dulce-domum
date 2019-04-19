package com.dulcedomum.dominio.familia.pessoa;

import com.dulcedomum.dominio.familia.pessoa.renda.Renda;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class RendaTest {

    private String pessoaId;
    private BigDecimal valor;

    @Before
    public void setUp() {
        pessoaId = UUID.randomUUID().toString();
        valor = BigDecimal.valueOf(1000.0);
    }

    @Test
    public void deveTerOIdentificadorDaPessoa() {
        String pessoaIdEsperado = UUID.randomUUID().toString();

        Renda renda = Renda.criar(pessoaIdEsperado, valor);

        assertThat(renda.getPessoaId()).isEqualTo(pessoaIdEsperado);
    }

    @Test
    public void deveTerOValorEmReais() {
        BigDecimal valorEsperado = BigDecimal.valueOf(1500.0);

        Renda renda = Renda.criar(pessoaId, valorEsperado);

        assertThat(renda.getValor()).isEqualTo(valorEsperado);
    }

    @Test
    public void naoDeveCriarARendaComIdentificadorDaPessoaInvalido() {
        String pessoaIdInvalido = null;

        Throwable excecaoLancada = catchThrowable(() -> Renda.criar(pessoaIdInvalido, valor));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o identificador da pessoa para registrar sua renda.");
    }

    @Test
    public void naoDeveCriarARendaComIdentificadorDaPessoaVazio() {
        String pessoaIdVazio = "";

        Throwable excecaoLancada = catchThrowable(() -> Renda.criar(pessoaIdVazio, valor));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o identificador da pessoa para registrar sua renda.");
    }

    @Test
    public void naoDeveCriarARendaComValorInvalido() {
        BigDecimal valorInvalido = null;

        Throwable excecaoLancada = catchThrowable(() -> Renda.criar(pessoaId, valorInvalido));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("É necessário informar o valor da renda da pessoa.");
    }

    @Test
    public void naoDeveCriarARendaComValorZerado() {
        BigDecimal valorZerado = BigDecimal.ZERO;

        Throwable excecaoLancada = catchThrowable(() -> Renda.criar(pessoaId, valorZerado));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O valor da renda de uma pessoa deve ser maior que zero.");
    }

    @Test
    public void naoDeveCriarARendaComValorMenorQueZero() {
        BigDecimal valorZerado = BigDecimal.valueOf(-500.0);

        Throwable excecaoLancada = catchThrowable(() -> Renda.criar(pessoaId, valorZerado));

        assertThat(excecaoLancada).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O valor da renda de uma pessoa deve ser maior que zero.");
    }
}
