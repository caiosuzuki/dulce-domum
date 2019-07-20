package com.dulcedomum.apresentacao.recurso.familia;

import com.dulcedomum.aplicacao.consulta.FamiliaDTO;
import com.dulcedomum.apresentacao.recurso.base.ConfirmacaoDeSucessoHttpDTO;
import com.dulcedomum.apresentacao.restbase.ColecaoRest;
import com.dulcedomum.apresentacao.restbase.DesserializadorColecaoRest;
import com.dulcedomum.apresentacao.restbase.DesserializadorElementoRest;
import com.dulcedomum.apresentacao.restbase.ElementoRest;
import com.dulcedomum.base.TesteDeIntegracao;
import com.dulcedomum.dominio.familia.Familia;
import com.dulcedomum.dominio.familia.FamiliaBuilder;
import com.dulcedomum.dominio.familia.StatusDaFamilia;
import com.dulcedomum.dominio.familia.pessoa.Pessoa;
import com.dulcedomum.infraestrutura.familia.FamiliaRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void deveConsultarAsFamilias() {
        Familia primeiraFamilia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        familiaRepository.save(primeiraFamilia);
        Familia segundaFamilia = FamiliaBuilder.novo().comStatus(StatusDaFamilia.CADASTRO_VALIDO).criar();
        familiaRepository.save(segundaFamilia);

        ResponseEntity<String> response = this.restTemplate.getForEntity(RECURSO, String.class);
        ColecaoRest colecaoRest = DesserializadorColecaoRest.criar(FamiliaDTO.class).desserializar(response.getBody());
        List<FamiliaDTO> familiasDTOs = colecaoRest.getElementos().stream().map(elementoRest -> (FamiliaDTO) elementoRest.getConteudo()).collect(Collectors.toList());

        Assertions.assertThat(familiasDTOs).hasSize(2);
        Assertions.assertThat(familiasDTOs.get(0).id).isEqualTo(primeiraFamilia.getId());
        Assertions.assertThat(familiasDTOs.get(1).id).isEqualTo(segundaFamilia.getId());
    }

    private AdicionaFamiliaHttpDTO criarAdicionaFamiliaHttpDTO() {
        AdicionaFamiliaHttpDTO adicionaFamiliaHttpDTO = new AdicionaFamiliaHttpDTO();
        adicionaFamiliaHttpDTO.pessoasDaFamilia = criarPessoasDaFamiliaHttpDTOs();
        adicionaFamiliaHttpDTO.status = "0";
        return adicionaFamiliaHttpDTO;
    }

    private List<PessoaDaFamiliaHttpDTO> criarPessoasDaFamiliaHttpDTOs() {
        PessoaDaFamiliaHttpDTO pessoaDaFamiliaHttpDTO = new PessoaDaFamiliaHttpDTO();
        pessoaDaFamiliaHttpDTO.nome = "Elliot Alderson";
        pessoaDaFamiliaHttpDTO.tipo = "Pretendente";
        pessoaDaFamiliaHttpDTO.dataDeNascimento = LocalDate.of(1986, 9, 17);
        pessoaDaFamiliaHttpDTO.valorDaRenda = BigDecimal.valueOf(1500.0);
        return singletonList(pessoaDaFamiliaHttpDTO);
    }

    private void comparaAtributosDePessoa(Pessoa pessoa, PessoaDaFamiliaHttpDTO pessoaDaFamiliaHttpDTO) {
        Assertions.assertThat(pessoa.getNome()).isEqualTo(pessoaDaFamiliaHttpDTO.nome);
        Assertions.assertThat(pessoa.getTipo().getDescricao()).isEqualTo(pessoaDaFamiliaHttpDTO.tipo);
        Assertions.assertThat(pessoa.getDataDeNascimento()).isEqualTo(pessoaDaFamiliaHttpDTO.dataDeNascimento);
        Assertions.assertThat(pessoa.getValorDaRenda().floatValue()).isEqualTo(pessoaDaFamiliaHttpDTO.valorDaRenda.floatValue());
    }
}
