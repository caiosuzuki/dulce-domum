package com.dulcedomum.dominio.familia.selecao;

import java.time.LocalDate;

public class DadosDaSelecaoDaFamilia {
    private final Integer quantidadeDeCriteriosAtendidos;
    private final Integer pontuacao;
    private final LocalDate dataDaSelecao;

    private DadosDaSelecaoDaFamilia(Integer quantidadeDeCriteriosAtendidos,
                                   Integer pontuacao) {
        this.quantidadeDeCriteriosAtendidos = quantidadeDeCriteriosAtendidos;
        this.pontuacao = pontuacao;
        this.dataDaSelecao = LocalDate.now();
    }

    public static DadosDaSelecaoDaFamilia criar(Integer quantidadeDeCriteriosAtendidos,
                                                Integer pontuacao) {
        validarCamposObrigatorios(quantidadeDeCriteriosAtendidos, pontuacao);
        return new DadosDaSelecaoDaFamilia(quantidadeDeCriteriosAtendidos, pontuacao);
    }

    private static void validarCamposObrigatorios(Integer quantidadeDeCriteriosAtendidos, Integer pontuacao) {
        if (quantidadeDeCriteriosAtendidos == null) {
            throw new IllegalArgumentException("Não é possível criar os dados da seleção da família sem a quantidade de critérios atendidos.");
        }
        if (pontuacao == null) {
            throw new IllegalArgumentException("Não é possível criar os dados da seleção da família sem a pontuação.");
        }
    }

    public Integer getQuantidadeDeCriteriosAtendidos() {
        return quantidadeDeCriteriosAtendidos;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public LocalDate getDataDaSelecao() {
        return dataDaSelecao;
    }
}
