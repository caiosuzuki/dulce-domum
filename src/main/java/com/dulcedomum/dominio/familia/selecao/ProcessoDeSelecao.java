package com.dulcedomum.dominio.familia.selecao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.StatusDaFamilia;
import com.dulcedomum.dominio.familia.pontuacao.CalculaPontuacoesDasFamilias;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class ProcessoDeSelecao {

    private CalculaPontuacoesDasFamilias calculaPontuacoesDasFamilias;

    private boolean encerrado;

    private LocalDate dataDeEncerramento;

    @Autowired
    public ProcessoDeSelecao(CalculaPontuacoesDasFamilias calculaPontuacoesDasFamilias) {
        this.calculaPontuacoesDasFamilias = calculaPontuacoesDasFamilias;
        this.encerrado = false;
    }

    public Map<String, Integer> selecionar(List<Familia> familias) {
        List<Familia> familiasComCadastroValido = obterFamiliasComCadastroValido(familias);
        return calculaPontuacoesDasFamilias.calcular(familiasComCadastroValido);
    }

    private List<Familia> obterFamiliasComCadastroValido(List<Familia> familias) {
        return familias.stream().filter(familia -> familia.getStatus().equals(StatusDaFamilia.CADASTRO_VALIDO)).collect(toList());
    }

    public boolean estaEncerrado() {
        return encerrado;
    }

    public void encerrar() {
        this.dataDeEncerramento = LocalDate.now();
        this.encerrado = true;
    }

    public LocalDate getDataDeEncerramento() {
        return dataDeEncerramento;
    }
}
