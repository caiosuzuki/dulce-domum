package com.dulcedomum.aplicacao.comando.familia.adicionafamilia;

import com.dulcedomum.aplicacao.comando.base.Comando;

import java.util.List;

public class AdicionarFamilia implements Comando {

    private List<PessoaDaFamilia> pessoasDaFamilia;
    private List<RendaDePessoaDaFamilia> rendasDePessoasDaFamilia;
    private String status;

    public AdicionarFamilia(List<PessoaDaFamilia> pessoasDaFamilia,
                            List<RendaDePessoaDaFamilia> rendasDePessoasDaFamilia,
                            String status) {
        this.pessoasDaFamilia = pessoasDaFamilia;
        this.rendasDePessoasDaFamilia = rendasDePessoasDaFamilia;
        this.status = status;
    }

    public List<PessoaDaFamilia> getPessoasDaFamilia() {
        return pessoasDaFamilia;
    }

    public List<RendaDePessoaDaFamilia> getRendasDePessoasDaFamilia() {
        return rendasDePessoasDaFamilia;
    }

    public String getStatus() {
        return status;
    }
}
