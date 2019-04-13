package dominio.pessoa;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PessoaTeste {

    private String id;
    private String nome;
    private TipoDePessoa tipo;
    private LocalDate dataDeNascimento;

    @Before
    public void setUp() {
        id = UUID.randomUUID().toString();
        nome = "Jonas Borba";
        tipo = TipoDePessoa.DEPENDENTE;
        dataDeNascimento = LocalDate.of(1990, 5, 3);
    }

    @Test
    public void deveTerUmId() {
        String idEsperado = UUID.randomUUID().toString();

        Pessoa pessoa = new Pessoa(idEsperado, nome, tipo, dataDeNascimento);

        assertThat(pessoa.id()).isEqualTo(idEsperado);
    }

    @Test
    public void deveTerUmNome() {
        String nomeEsperado = "Sergililsson";

        Pessoa pessoa = new Pessoa(id, nomeEsperado, tipo, dataDeNascimento);

        assertThat(pessoa.getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    public void deveTerUmTipo() {
        TipoDePessoa tipoEsperado = TipoDePessoa.PRETENDENTE;

        Pessoa pessoa = new Pessoa(id, nome, tipoEsperado, dataDeNascimento);

        assertThat(pessoa.getTipo()).isEqualTo(tipoEsperado);
    }

    @Test
    public void deveTerUmaDataDeNascimento() {
        LocalDate dataDeNascimentoEsperada = LocalDate.of(1997, 3, 20);

        Pessoa pessoa = new Pessoa(id, nome, tipo, dataDeNascimentoEsperada);

        assertThat(pessoa.getDataDeNascimento()).isEqualTo(dataDeNascimentoEsperada);
    }
}
