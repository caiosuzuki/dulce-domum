package com.dulcedomum.aplicacao.comando.familia.adicionafamilia;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PessoaDaFamiliaTest {

    @Test
    public void deveCriarUmaPessoaDaFamilia() {
        String nomeEsperado = "João das Neves";
        String tipoEsperado = "pretendente";
        LocalDate dataDeNascimentoEsperada = LocalDate.now();

        PessoaDaFamilia pessoaDaFamilia = new PessoaDaFamilia(nomeEsperado, tipoEsperado, dataDeNascimentoEsperada);

        assertThat(pessoaDaFamilia.getNome()).isEqualTo(nomeEsperado);
        assertThat(pessoaDaFamilia.getTipo()).isEqualTo(tipoEsperado);
        assertThat(pessoaDaFamilia.getDataDeNascimento()).isEqualTo(dataDeNascimentoEsperada);

    }
}
