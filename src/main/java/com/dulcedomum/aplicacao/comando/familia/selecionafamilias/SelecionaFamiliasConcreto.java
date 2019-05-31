package com.dulcedomum.aplicacao.comando.familia.selecionafamilias;

import com.dulcedomum.aplicacao.comando.base.ConfirmacaoDeSucesso;
import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.selecao.SelecionaFamiliasServicoDeDominio;
import com.dulcedomum.infraestrutura.familia.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelecionaFamiliasConcreto implements SelecionaFamilias{

    @Autowired
    private FamiliaRepository familiaRepository;

    @Autowired
    private SelecionaFamiliasServicoDeDominio selecionaFamiliasServicoDeDominio;

    @Override
    public ConfirmacaoDeSucesso executar(SelecionarFamilias comando) {
        List<Familia> familiasParaSelecionar = familiaRepository.obterPorIdsDasFamilias(comando.getIdsDasFamilias());
        List<Familia> familiasSelecionadas = selecionaFamiliasServicoDeDominio.selecionar(familiasParaSelecionar);
        familiaRepository.saveAll(familiasSelecionadas);
        return ConfirmacaoDeSucesso.criar(familiasParaSelecionar.get(0).getId());
    }
}
