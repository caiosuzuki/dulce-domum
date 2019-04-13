package com.dulcedomum.dominio.familia.pessoa;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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

        Renda renda = new Renda(pessoaIdEsperado, valor);

        assertThat(renda.getPessoaId()).isEqualTo(pessoaIdEsperado);
    }

    @Test
    public void deveTerOValorEmReais() {
        BigDecimal valorEsperado = BigDecimal.valueOf(1500.0);

        Renda renda = new Renda(pessoaId, valorEsperado);

        assertThat(renda.getValor()).isEqualTo(valorEsperado);
    }
}
