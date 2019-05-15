package com.dulcedomum.apresentacao.recurso.familia;

import com.dulcedomum.apresentacao.recurso.base.ConfirmacaoDeSucessoHttpDTO;
import com.dulcedomum.base.TesteDeIntegracao;
import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import com.dulcedomum.dominio.familia.StatusDaFamilia;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.dominio.familia.pessoa.renda.Renda;
import com.dulcedomum.infraestrutura.FamiliaRepository;
import com.dulcedomum.rest.DesserializadorElementoRest;
import com.dulcedomum.rest.ElementoRest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class FamiliaRestTest extends TesteDeIntegracao {

    private static final String RECURSO = "/api/familia";

    @Autowired
    FamiliaRepository familiaRepository;

    @Test
    public void deveAdicionarUmaFamilia() {
        AdicionaFamiliaHttpDTO adicionaFamiliaHttpDTO = criarAdicionaFamiliaHttpDTO();
        HttpEntity<AdicionaFamiliaHttpDTO> request = new HttpEntity<>(adicionaFamiliaHttpDTO);

        ResponseEntity<String> response = this.restTemplate.postForEntity(RECURSO, request, String.class);
        ElementoRest elementoRest = DesserializadorElementoRest.desserializar(response.getBody(), ConfirmacaoDeSucessoHttpDTO.class);
        ConfirmacaoDeSucessoHttpDTO confirmacaoDeSucessoHttpDTO = (ConfirmacaoDeSucessoHttpDTO) elementoRest.getConteudo();
        String familiaId = confirmacaoDeSucessoHttpDTO.getId();
        Familia familiaAdicionada = familiaRepository.obterPorFamiliaId(familiaId);

        assertThat(familiaAdicionada.getStatus().getCodigo()).isEqualTo(adicionaFamiliaHttpDTO.status);
        comparaAtributosDePessoa(familiaAdicionada.getPessoas().get(0), adicionaFamiliaHttpDTO.pessoasDaFamilia.get(0));
        comparaAtributosDeRenda(familiaAdicionada.getRendas().get(0), adicionaFamiliaHttpDTO.rendasDePessoasDaFamilia.get(0));
    }

    @Test
    public void deveSelecionarAsFamilias() {
        Familia primeiraFamilia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        familiaRepository.save(primeiraFamilia);
        Familia segundaFamilia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        familiaRepository.save(segundaFamilia);
        List<String> idsDasFamilias = asList(primeiraFamilia.getId(), segundaFamilia.getId());
        SelecionaFamiliasHttpDTO selecionaFamiliasHttpDTO = new SelecionaFamiliasHttpDTO();
        selecionaFamiliasHttpDTO.idsDasFamilias = idsDasFamilias;
        HttpEntity<SelecionaFamiliasHttpDTO> request = new HttpEntity<>(selecionaFamiliasHttpDTO);

        this.restTemplate.put(RECURSO, request, String.class);
        Familia primeiraFamiliaSelecionada = familiaRepository.obterPorFamiliaId(primeiraFamilia.getId());
        Familia segundaFamiliaSelecionada = familiaRepository.obterPorFamiliaId(segundaFamilia.getId());

        assertThat(primeiraFamiliaSelecionada.getDadosDaSelecao()).isNotNull();
        assertThat(segundaFamiliaSelecionada.getDadosDaSelecao()).isNotNull();
    }

    private AdicionaFamiliaHttpDTO criarAdicionaFamiliaHttpDTO() {
        AdicionaFamiliaHttpDTO adicionaFamiliaHttpDTO = new AdicionaFamiliaHttpDTO();
        adicionaFamiliaHttpDTO.pessoasDaFamilia = criarPessoasDaFamiliaHttpDTOs();
        adicionaFamiliaHttpDTO.rendasDePessoasDaFamilia = criarRendasDePessoasDaFamiliaHttpDTOs();
        adicionaFamiliaHttpDTO.status = "0";
        return adicionaFamiliaHttpDTO;
    }

    private List<PessoaDaFamiliaHttpDTO> criarPessoasDaFamiliaHttpDTOs() {
        PessoaDaFamiliaHttpDTO pessoaDaFamiliaHttpDTO = new PessoaDaFamiliaHttpDTO();
        pessoaDaFamiliaHttpDTO.nome = "Elliot Alderson";
        pessoaDaFamiliaHttpDTO.tipo = "pretendente";
        pessoaDaFamiliaHttpDTO.dataDeNascimento = LocalDate.of(1986, 9, 17);
        return singletonList(pessoaDaFamiliaHttpDTO);
    }

    private List<RendaDePessoaDaFamiliaHttpDTO> criarRendasDePessoasDaFamiliaHttpDTOs() {
        RendaDePessoaDaFamiliaHttpDTO rendaDePessoaDaFamiliaHttpDTO = new RendaDePessoaDaFamiliaHttpDTO();
        rendaDePessoaDaFamiliaHttpDTO.pessoaId = UUID.randomUUID().toString();
        rendaDePessoaDaFamiliaHttpDTO.valor = BigDecimal.valueOf(1350.00);
        return singletonList(rendaDePessoaDaFamiliaHttpDTO);
    }

    private void comparaAtributosDePessoa(Pessoa pessoa, PessoaDaFamiliaHttpDTO pessoaDaFamiliaHttpDTO) {
        Assertions.assertThat(pessoa.getNome()).isEqualTo(pessoaDaFamiliaHttpDTO.nome);
        Assertions.assertThat(pessoa.getTipo().getDescricao()).isEqualTo(pessoaDaFamiliaHttpDTO.tipo);
        Assertions.assertThat(pessoa.getDataDeNascimento()).isEqualTo(pessoaDaFamiliaHttpDTO.dataDeNascimento);
    }

    private void comparaAtributosDeRenda(Renda renda, RendaDePessoaDaFamiliaHttpDTO rendaDePessoaDaFamiliaHttpDTO) {
        Assertions.assertThat(renda.getValor().floatValue()).isEqualTo(rendaDePessoaDaFamiliaHttpDTO.valor.floatValue());
        Assertions.assertThat(renda.getPessoaId()).isEqualTo(rendaDePessoaDaFamiliaHttpDTO.pessoaId);
    }
}
