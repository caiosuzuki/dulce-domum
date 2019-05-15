package com.dulcedomum.aplicacao.comando.familia.adicionafamilia;

import com.dulcedomum.aplicacao.comando.base.Comando;

import java.util.List;

public class AdicionarFamilia implements Comando {

    private List<PessoaDaFamilia> pessoasDaFamilia;
    private String status;

    public AdicionarFamilia(List<PessoaDaFamilia> pessoasDaFamilia,
                            String status) {
        this.pessoasDaFamilia = pessoasDaFamilia;
        this.status = status;
    }

    public List<PessoaDaFamilia> getPessoasDaFamilia() {
        return pessoasDaFamilia;
    }

    public String getStatus() {
        return status;
    }
}
