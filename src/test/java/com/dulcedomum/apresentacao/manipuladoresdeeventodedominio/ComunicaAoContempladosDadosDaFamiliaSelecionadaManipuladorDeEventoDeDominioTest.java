package com.dulcedomum.apresentacao.manipuladoresdeeventodedominio;

import com.dulcedomum.dominio.familia.eventodedominio.FamiliaSelecionada;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ComunicaAoContempladosDadosDaFamiliaSelecionadaManipuladorDeEventoDeDominioTest {

    ComunicaAoContempladosDadosDaFamiliaSelecionadaManipuladorDeEventoDeDominio manipulador;
    private ByteArrayOutputStream conteudoDoOutputStream;

    @Before
    public void setUp() {
        conteudoDoOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(conteudoDoOutputStream));
    }

    @Test
    public void devePrintarAsInformacoesDaFamiliaQueFoiSelecionada() {
        manipulador = new ComunicaAoContempladosDadosDaFamiliaSelecionadaManipuladorDeEventoDeDominio();
        String familiaId = UUID.randomUUID().toString();
        Integer quantidadeDeCriteriosAtendidos = 2;
        Integer pontuacaoTotal = 7;
        LocalDate dataDaSelecao = LocalDate.now();
        FamiliaSelecionada familiaSelecionada = FamiliaSelecionada.criar(familiaId, quantidadeDeCriteriosAtendidos,
                pontuacaoTotal, dataDaSelecao);

        manipulador.manipular(familiaSelecionada);

        assertThat(conteudoDoOutputStream.toString()).contains("Identificador da família: " + familiaId);
        assertThat(conteudoDoOutputStream.toString()).contains("Quantidade de critérios atendidos: " + quantidadeDeCriteriosAtendidos);
        assertThat(conteudoDoOutputStream.toString()).contains("Pontuacao total: " + pontuacaoTotal);
        assertThat(conteudoDoOutputStream.toString()).contains("Data da seleção: " + dataDaSelecao);
    }
}