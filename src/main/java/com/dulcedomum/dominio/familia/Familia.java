package com.dulcedomum.dominio.familia;

import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.TipoDePessoa;
import com.dulcedomum.dominio.familia.pessoa.renda.Renda;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Familia {

    private static int QUANTIDADE_MAXIMA_DE_CONJUGES = 1;
    private String familiaId;
    private List<Pessoa> pessoas;
    private List<Renda> rendas;
    private StatusDaFamilia status;

    private Familia(String familiaId,
                    List<Pessoa> pessoas,
                    List<Renda> rendas,
                    StatusDaFamilia status) {
        this.familiaId = familiaId;
        this.pessoas = pessoas;
        this.rendas = rendas;
        this.status = status;
    }

    public static Familia criar(String familiaId,
                                List<Pessoa> pessoas,
                                List<Renda> rendas,
                                StatusDaFamilia status) {
        validarSeExisteUmPretendente(pessoas);
        validarSeFamiliaNaoTemMaisDeUmPretendente(pessoas);
        return new Familia(familiaId, pessoas, rendas, status);
    }

    private static void validarSeExisteUmPretendente(List<Pessoa> pessoas) {
        Optional<Pessoa> pretendenteEncontrado = pessoas.stream().filter(pessoa -> pessoa.getTipo().equals(TipoDePessoa.PRETENDENTE)).findAny();
        if (!pretendenteEncontrado.isPresent()) {
            throw new IllegalArgumentException("Não é possível registrar uma família sem um pretendente.");
        }
    }

    private static void validarSeFamiliaNaoTemMaisDeUmPretendente(List<Pessoa> pessoas) {
        long quantidadeDeConjuges = pessoas.stream().filter(pessoa -> pessoa.getTipo().equals(TipoDePessoa.CONJUGE)).count();
        if (quantidadeDeConjuges > QUANTIDADE_MAXIMA_DE_CONJUGES) {
            throw new IllegalArgumentException("Não é possível registrar uma família com mais de um cônjuge.");
        }
    }

    public BigDecimal calcularValorTotalDaRenda() {
        return rendas.stream().map(renda -> renda.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getId() {
        return familiaId;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public List<Renda> getRendas() {
        return rendas;
    }

    public StatusDaFamilia getStatus() {
        return status;
    }
}
