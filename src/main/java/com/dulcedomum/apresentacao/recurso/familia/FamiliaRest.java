package com.dulcedomum.apresentacao.recurso.familia;

import com.dulcedomum.aplicacao.comando.base.ConfirmacaoDeSucesso;
import com.dulcedomum.aplicacao.comando.familia.adicionafamilia.AdicionaFamilia;
import com.dulcedomum.aplicacao.comando.familia.adicionafamilia.AdicionarFamilia;
import com.dulcedomum.aplicacao.comando.familia.adicionafamilia.PessoaDaFamilia;
import com.dulcedomum.aplicacao.comando.familia.adicionafamilia.RendaDePessoaDaFamilia;
import com.dulcedomum.apresentacao.recurso.base.ConfirmacaoDeSucessoHttpDTO;
import com.dulcedomum.rest.ElementoRest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @POST
    public Response adicionarFamilia(AdicionaFamiliaHttpDTO httpDTO) {
        AdicionarFamilia comando = criarComandoParaAdicionarFamilia(httpDTO);
        ConfirmacaoDeSucesso confirmacaoDeSucesso = adicionaFamilia.executar(comando);
        ConfirmacaoDeSucessoHttpDTO confirmacaoDeSucessoHttpDTO = new ConfirmacaoDeSucessoHttpDTO(confirmacaoDeSucesso.getId());
        return Response.ok().entity(new ElementoRest(confirmacaoDeSucessoHttpDTO)).build();
    }

    private AdicionarFamilia criarComandoParaAdicionarFamilia(AdicionaFamiliaHttpDTO httpDTO) {
        List<PessoaDaFamilia> pessoasDaFamilia = criarPessoasDaFamilia(httpDTO);
        List<RendaDePessoaDaFamilia> rendasDePessoasDaFamilia = criarRendasDePessoasDaFamilia(httpDTO);
        return new AdicionarFamilia(pessoasDaFamilia, rendasDePessoasDaFamilia, httpDTO.status);
    }

    private List<PessoaDaFamilia> criarPessoasDaFamilia(AdicionaFamiliaHttpDTO httpDTO) {
        return httpDTO.pessoasDaFamilia.stream().map(pessoaDaFamiliaHttpDTO ->
                new PessoaDaFamilia(pessoaDaFamiliaHttpDTO.nome, pessoaDaFamiliaHttpDTO.tipo, pessoaDaFamiliaHttpDTO.dataDeNascimento))
                .collect(toList());
    }

    private List<RendaDePessoaDaFamilia> criarRendasDePessoasDaFamilia(AdicionaFamiliaHttpDTO httpDTO) {
        return httpDTO.rendasDePessoasDaFamilia.stream().map(rendaDePessoaDaFamiliaHttpDTO ->
                new RendaDePessoaDaFamilia(rendaDePessoaDaFamiliaHttpDTO.pessoaId, rendaDePessoaDaFamiliaHttpDTO.valor))
                .collect(toList());
    }
}
