package com.dulcedomum.aplicacao.comando.familia.adicionafamilia;

import com.dulcedomum.aplicacao.comando.base.ConfirmacaoDeSucesso;
import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.StatusDaFamilia;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.TipoDePessoa;
import com.dulcedomum.dominio.familia.pessoa.renda.Renda;
import com.dulcedomum.infraestrutura.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public class AdicionaFamiliaConcreto implements AdicionaFamilia {

    private FamiliaRepository familiaRepository;

    @Autowired
    public AdicionaFamiliaConcreto(FamiliaRepository familiaRepository) {
        this.familiaRepository = familiaRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ConfirmacaoDeSucesso executar(AdicionarFamilia comando) {
        List<Pessoa> pessoas = criarPessoas(comando.getPessoasDaFamilia());
        List<Renda> rendas = criarRendas(comando.getRendasDePessoasDaFamilia());
        StatusDaFamilia statusDaFamilia = StatusDaFamilia.valueOf(comando.getStatus());
        String familiaId = UUID.randomUUID().toString();
        Familia familia = Familia.criar(familiaId, pessoas, rendas, statusDaFamilia);
        familiaRepository.save(familia);
        return ConfirmacaoDeSucesso.criar(familiaId);
    }

    private List<Pessoa> criarPessoas(List<PessoaDaFamilia> pessoasDaFamilia) {
        return pessoasDaFamilia.stream().map(pessoaDaFamilia ->
                Pessoa.criar(pessoaDaFamilia.getNome(), TipoDePessoa.valueOf(pessoaDaFamilia.getTipo()),
                        pessoaDaFamilia.getDataDeNascimento())).collect(toList());
    }

    private List<Renda> criarRendas(List<RendaDePessoaDaFamilia> rendasDePessoasDaFamilia) {
        return rendasDePessoasDaFamilia.stream().map(rendaDePessoaDaFamilia ->
                Renda.criar(rendaDePessoaDaFamilia.getPessoaId(), rendaDePessoaDaFamilia.getValor())).collect(toList());
    }
}
