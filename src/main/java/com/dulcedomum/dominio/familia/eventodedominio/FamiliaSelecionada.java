package com.dulcedomum.dominio.familia.eventodedominio;

import com.dulcedomum.eventodedominiobase.EventoDeDominio;

import java.time.LocalDate;

public class FamiliaSelecionada implements EventoDeDominio {

    private final String familiaId;
    private final Integer quantidadeDeCriteriosAtendidos;
    private final Integer pontuacaoTotal;
    private final LocalDate dataDaSelecao;

    private FamiliaSelecionada(String familiaId,
                              Integer quantidadeDeCriteriosAtendidos,
                              Integer pontuacaoTotal,
                              LocalDate dataDaSelecao) {

        this.familiaId = familiaId;
        this.quantidadeDeCriteriosAtendidos = quantidadeDeCriteriosAtendidos;
        this.pontuacaoTotal = pontuacaoTotal;
        this.dataDaSelecao = dataDaSelecao;
    }

    public static FamiliaSelecionada criar(String familiaId,
                                           Integer quantidadeDeCriteriosAtendidos,
                                           Integer pontuacaoTotal,
                                           LocalDate dataDaSelecao) {
        validarCamposObrigatorios(familiaId, quantidadeDeCriteriosAtendidos, pontuacaoTotal, dataDaSelecao);
        return new FamiliaSelecionada(familiaId, quantidadeDeCriteriosAtendidos, pontuacaoTotal, dataDaSelecao);
    }

    private static void validarCamposObrigatorios(String familiaId,
                                      Integer quantidadeDeCriteriosAtendidos,
                                      Integer pontuacaoTotal,
                                      LocalDate dataDaSelecao) {
        if (familiaId == null) {
            throw new IllegalArgumentException("É obrigatório informar o identificador da família para criar o evento de família selecionada.");
        }
        if (quantidadeDeCriteriosAtendidos == null) {
            throw new IllegalArgumentException("É obrigatório informar a quantidade de critérios atendidos da família para criar o evento de família selecionada.");
        }
        if (pontuacaoTotal == null) {
            throw new IllegalArgumentException("É obrigatório informar a pontuação da família para criar o evento de família selecionada.");
        }
        if (dataDaSelecao == null) {
            throw new IllegalArgumentException("É obrigatório informar a data da seleção da família para criar o evento de família selecionada.");
        }
    }

    public String getFamiliaId() {
        return familiaId;
    }

    public Integer getQuantidadeDeCriteriosAtendidos() {
        return quantidadeDeCriteriosAtendidos;
    }

    public Integer getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public LocalDate getDataDaSelecao() {
        return dataDaSelecao;
    }
}
