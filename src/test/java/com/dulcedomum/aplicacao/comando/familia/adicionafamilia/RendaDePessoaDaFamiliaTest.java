package com.dulcedomum.aplicacao.comando.familia.adicionafamilia;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RendaDePessoaDaFamiliaTest {

    @Test
    public void deveCriarUmaRendaDePessoaDaFamilia() {
        String pessoaIdEsperado = UUID.randomUUID().toString();
        BigDecimal valorEsperado = BigDecimal.valueOf(1200.00);

        RendaDePessoaDaFamilia rendaDePessoaDaFamilia = new RendaDePessoaDaFamilia(pessoaIdEsperado, valorEsperado);

        assertThat(rendaDePessoaDaFamilia.getPessoaId()).isEqualTo(pessoaIdEsperado);
        assertThat(rendaDePessoaDaFamilia.getValor()).isEqualTo(valorEsperado);
    }
}
