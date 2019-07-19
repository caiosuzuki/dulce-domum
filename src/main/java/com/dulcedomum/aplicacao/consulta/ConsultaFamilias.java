package com.dulcedomum.aplicacao.consulta;

import com.dulcedomum.apresentacao.recurso.familia.PessoaDaFamiliaHttpDTO;
import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.infraestrutura.familia.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaFamilias {

    private FamiliaRepository familiaRepository;

    @Autowired
    public ConsultaFamilias(FamiliaRepository familiaRepository) {
        this.familiaRepository = familiaRepository;
    }

    public List<FamiliaDTO> buscarTodas() {
        List<Familia> familias = (List<Familia>) familiaRepository.findAll();
        return familias.stream().map(familia -> montarFamiliaDTO(familia)).collect(Collectors.toList());
    }

    private FamiliaDTO montarFamiliaDTO(Familia familia) {
        FamiliaDTO familiaDTO = new FamiliaDTO();
        familiaDTO.status = familia.getStatus().getDescricao();
        familiaDTO.pessoasDaFamiliaHttpDTOs = new ArrayList<>();
        familiaDTO.pessoasDaFamiliaHttpDTOs = familia.getPessoas().stream().map(pessoa -> montarPessoaDaFamiliaHttpDTO(pessoa)).collect(Collectors.toList());
        return familiaDTO;
    }

    private PessoaDaFamiliaHttpDTO montarPessoaDaFamiliaHttpDTO(Pessoa pessoa) {
        PessoaDaFamiliaHttpDTO pessoaDaFamiliaHttpDTO = new PessoaDaFamiliaHttpDTO();
        pessoaDaFamiliaHttpDTO.nome = pessoa.getNome();
        pessoaDaFamiliaHttpDTO.tipo = pessoa.getTipo().getDescricao();
        pessoaDaFamiliaHttpDTO.dataDeNascimento = pessoa.getDataDeNascimento();
        pessoaDaFamiliaHttpDTO.valorDaRenda = pessoa.getValorDaRenda();
        return pessoaDaFamiliaHttpDTO;
    }
}
