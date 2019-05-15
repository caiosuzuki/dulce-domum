package com.dulcedomum.dominio.familia;

import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.PessoaBuilder;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;

public class FamiliaBuilder {
    private String familiaId = UUID.randomUUID().toString();
    private List<Pessoa> pessoas = singletonList(PessoaBuilder.novo().criar());
    private StatusDaFamilia status = StatusDaFamilia.CADASTRO_INCOMPLETO;

    public static FamiliaBuilder novo() {
        return new FamiliaBuilder();
    }

    public Familia criar() {
        return Familia.criar(familiaId, pessoas, status);
    }

    public FamiliaBuilder comFamiliaId(String familiaId) {
        this.familiaId = familiaId;
        return this;
    }

    public FamiliaBuilder comPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
        return this;
    }

    public FamiliaBuilder comStatus(StatusDaFamilia status) {
        this.status = status;
        return this;
    }
}
