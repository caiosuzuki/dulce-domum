package com.dulcedomum.apresentacao.manipuladoresdeeventodedominio;

import com.dulcedomum.dominio.familia.eventodedominio.FamiliaSelecionada;
import com.dulcedomum.eventodedominiobase.ManipuladorDeEventoDeDominio;
import org.springframework.stereotype.Service;

@Service
public class ComunicaAoContempladosDadosDaFamiliaSelecionadaManipuladorDeEventoDeDominio implements ManipuladorDeEventoDeDominio<FamiliaSelecionada> {

    // Aqui deveria ser usado um serviço de mensageria para comunicar com outro módulo
    @Override
    public void manipular(FamiliaSelecionada familiaSelecionada) {
        System.out.println("Identificador da família: " + familiaSelecionada.getFamiliaId());
        System.out.println("Quantidade de critérios atendidos: " + familiaSelecionada.getQuantidadeDeCriteriosAtendidos());
        System.out.println("Pontuacao total: " + familiaSelecionada.getPontuacaoTotal());
        System.out.println("Data da seleção: " + familiaSelecionada.getDataDaSelecao());
    }
}
