package dominio.pessoa;

import java.time.LocalDate;

public class Pessoa {

    private String id;
    private String nome;
    private TipoDePessoa tipo;
    private LocalDate dataDeNascimento;

    public Pessoa(String id, String nome, TipoDePessoa tipo, LocalDate dataDeNascimento) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNome() {
        return nome;
    }

    public TipoDePessoa getTipo() {
        return tipo;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String id() {
        return id;
    }
}
