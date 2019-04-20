package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.PessoaBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class AvaliadorDeCriterioDeIdadeDoPretendenteTest {

    private static final int PONTUACAO_DE_FAVORECIDO = 3;
    private AvaliadorDeCriterioDeIdadeDoPretendente avaliadorDeCriterioDeIdadeDoPretendente;

    @Before
    public void setUp() {
        avaliadorDeCriterioDeIdadeDoPretendente = new AvaliadorDeCriterioDeIdadeDoPretendente();
    }

    @Test
    public void devePontuarComoFavorecidoApenasAFamiliaQueTemOPretendenteMaisVelho() {
        LocalDate dataDeNascimentoDoPretendenteMaisVelho = LocalDate.of(1980, 3, 2);
        Pessoa pretendenteMaisVelho = PessoaBuilder.novo().comDataDeNascimento(dataDeNascimentoDoPretendenteMaisVelho).criar();
        Familia familiaComPretendenteMaisVelho = FamiliaBuilder.novo().comPessoas(singletonList(pretendenteMaisVelho)).criar();
        LocalDate dataDeNascimentoDoPretendenteMaisNovo = dataDeNascimentoDoPretendenteMaisVelho.plusYears(3);
        Pessoa pretendenteMaisNovo = PessoaBuilder.novo().comDataDeNascimento(dataDeNascimentoDoPretendenteMaisNovo).criar();
        Familia familiaComPretendenteMaisNovo = FamiliaBuilder.novo().comPessoas(singletonList(pretendenteMaisNovo)).criar();
        List<Familia> familias = asList(familiaComPretendenteMaisNovo, familiaComPretendenteMaisVelho);

        Map<String, Integer> mapaDeFamiliaIdsEPontuacoes = avaliadorDeCriterioDeIdadeDoPretendente.calcularPontuacoesPeloCriterio(familias);
        Integer pontuacaoDaFamiliaComPretendenteMaisVelho = mapaDeFamiliaIdsEPontuacoes.get(familiaComPretendenteMaisVelho.getId());

        assertThat(pontuacaoDaFamiliaComPretendenteMaisVelho).isEqualTo(PONTUACAO_DE_FAVORECIDO);
    }
}
