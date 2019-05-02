package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AvaliadorDeCriterioDeQuantidadeDeDependentes implements AvaliadorDeCriterioDePontuacaoDaFamilia {

    private static final Integer DEZOITO_ANOS = 18;
    private static final Integer TRES_DEPENDENTES = 3;
    private static final Integer TRES_PONTOS = 3;
    private static final Integer UM_DEPENDENTE = 1;
    private static final Integer DOIS_PONTOS = 2;
    private static final Integer ZERO_PONTOS = 0;

    @Override
    public Integer calcularPontuacaoPeloCriterio(Familia familia) {

        Integer quantidadeDeDependentesMenoresDe18Anos = obterQuantidadeDeDependentesMenoresDe18Anos(familia);
        if (quantidadeDeDependentesMenoresDe18Anos.compareTo(TRES_DEPENDENTES) >= 0) {
            return TRES_PONTOS;
        } else if (quantidadeDeDependentesMenoresDe18Anos.compareTo(UM_DEPENDENTE) >= 0) {
            return DOIS_PONTOS;
        }
        return ZERO_PONTOS;
    }

    private Integer obterQuantidadeDeDependentesMenoresDe18Anos(Familia familia) {
        return familia.getDependentes().stream().filter(pessoa ->
                pessoa.getIdade().compareTo(DEZOITO_ANOS) < 0).collect(Collectors.toList()).size();
    }
}
