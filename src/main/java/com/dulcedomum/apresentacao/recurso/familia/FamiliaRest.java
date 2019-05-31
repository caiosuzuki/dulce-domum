package com.dulcedomum.apresentacao.recurso.familia;

import com.dulcedomum.aplicacao.comando.base.ConfirmacaoDeSucesso;
import com.dulcedomum.aplicacao.comando.familia.adicionafamilia.AdicionaFamilia;
import com.dulcedomum.aplicacao.comando.familia.adicionafamilia.AdicionarFamilia;
import com.dulcedomum.aplicacao.comando.familia.adicionafamilia.PessoaDaFamilia;
import com.dulcedomum.aplicacao.comando.familia.selecionafamilias.SelecionaFamilias;
import com.dulcedomum.aplicacao.comando.familia.selecionafamilias.SelecionarFamilias;
import com.dulcedomum.apresentacao.recurso.base.ConfirmacaoDeSucessoHttpDTO;
import com.dulcedomum.apresentacao.restbase.ElementoRest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("familia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FamiliaRest {

    @Autowired
    private AdicionaFamilia adicionaFamilia;

    @Autowired
    private SelecionaFamilias selecionaFamilias;

    @POST
    public Response adicionarFamilia(AdicionaFamiliaHttpDTO httpDTO) {
        AdicionarFamilia comando = criarComandoParaAdicionarFamilia(httpDTO);
        ConfirmacaoDeSucesso confirmacaoDeSucesso = adicionaFamilia.executar(comando);
        ConfirmacaoDeSucessoHttpDTO confirmacaoDeSucessoHttpDTO = new ConfirmacaoDeSucessoHttpDTO(confirmacaoDeSucesso.getId());
        return Response.ok().entity(new ElementoRest(confirmacaoDeSucessoHttpDTO)).build();
    }

    @PUT
    public Response selecionarFamilias(SelecionaFamiliasHttpDTO httpDTO) {
        SelecionarFamilias comando = new SelecionarFamilias(httpDTO.idsDasFamilias);
        selecionaFamilias.executar(comando);
        return Response.ok().build();
    }

    private AdicionarFamilia criarComandoParaAdicionarFamilia(AdicionaFamiliaHttpDTO httpDTO) {
        List<PessoaDaFamilia> pessoasDaFamilia = criarPessoasDaFamilia(httpDTO);
        return new AdicionarFamilia(pessoasDaFamilia, httpDTO.status);
    }

    private List<PessoaDaFamilia> criarPessoasDaFamilia(AdicionaFamiliaHttpDTO httpDTO) {
        return httpDTO.pessoasDaFamilia.stream().map(pessoaDaFamiliaHttpDTO ->
                new PessoaDaFamilia(pessoaDaFamiliaHttpDTO.nome, pessoaDaFamiliaHttpDTO.tipo, pessoaDaFamiliaHttpDTO.dataDeNascimento, pessoaDaFamiliaHttpDTO.valorDaRenda))
                .collect(toList());
    }
}
