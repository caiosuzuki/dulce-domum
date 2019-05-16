package com.dulcedomum.dominio.familia.selecao;

public class DadosDaSelecaoDaFamiliaBuilder {

    private Integer quantidadeDeCriteriosAtendidos = 3;
    private Integer pontuacao = 11;

    public static DadosDaSelecaoDaFamiliaBuilder novo() {
        return new DadosDaSelecaoDaFamiliaBuilder();
    }

    public DadosDaSelecaoDaFamilia criar() {
        return DadosDaSelecaoDaFamilia.criar(quantidadeDeCriteriosAtendidos, pontuacao);
    }

    public DadosDaSelecaoDaFamiliaBuilder comQuantidadeDeCriteriosAtendidos(Integer quantidadeDeCriteriosAtendidos) {
        this.quantidadeDeCriteriosAtendidos = quantidadeDeCriteriosAtendidos;
        return this;
    }

    public DadosDaSelecaoDaFamiliaBuilder comPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
        return this;
    }
}
