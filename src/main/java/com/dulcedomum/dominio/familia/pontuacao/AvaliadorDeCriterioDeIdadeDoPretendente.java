package com.dulcedomum.dominio.familia.pontuacao;

import com.dulcedomum.dominio.familia.Familia;
import org.springframework.stereotype.Component;

@Component
public class AvaliadorDeCriterioDeIdadeDoPretendente implements AvaliadorDeCriterioDePontuacaoDaFamilia {

    private static final int PONTUACAO_PARA_PRETENDENTE_DE_45_ANOS_OU_MAIS = 3;
    private static final int PONTUACAO_PARA_PRETENDENTE_DE_30_A_44_ANOS = 2;
    private static final int PONTUACAO_PARA_PRETENDENTE_ABAIXO_DE_30_ANOS = 1;
    private static final int QUARENTA_E_CINCO_ANOS = 45;
    private static final int TRINTA_ANOS = 30;

    @Override
    public Integer calcularPontuacaoPeloCriterio(Familia familia) {
        Integer idadeDoPretendente = familia.getPretendente().get().getIdade();
        if (idadeDoPretendente.compareTo(QUARENTA_E_CINCO_ANOS) >= 0) {
            return PONTUACAO_PARA_PRETENDENTE_DE_45_ANOS_OU_MAIS;
        } else if (idadeDoPretendente.compareTo(TRINTA_ANOS) >= 0) {
            return PONTUACAO_PARA_PRETENDENTE_DE_30_A_44_ANOS;
        } else {
            return PONTUACAO_PARA_PRETENDENTE_ABAIXO_DE_30_ANOS;
        }
    }
}
