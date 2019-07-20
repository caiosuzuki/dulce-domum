let idsDasFamiliasESeusPretendentes = []

function montaListaComTodosOsPretendentesDasFamilias() {
    axios.get('http://localhost:8080/api/familia')
    .then((resposta) => {
        const familiasConsultadas = resposta.data.elementos
        familiasConsultadas.forEach((familia) => {
            let pretendente = obterPretendenteDaFamilia(familia);
            idsDasFamiliasESeusPretendentes.push({pretendente, id: familia.conteudo.id});
        });
        let listaDePretendentes = document.querySelector('[data-js="lista-de-pretendentes-das-familias"]');
        idsDasFamiliasESeusPretendentes.forEach((idDaFamiliaEPretendente) => {
            incluirCheckboxDoPretendenteNaLista(listaDePretendentes, idDaFamiliaEPretendente);
        });
    })
    .catch((erro) => {
        window.alert('Ocorreu um erro ao buscar os pretendentes  das famílias. ', erro);
    });
}

function obterPretendenteDaFamilia(familia) {
    return familia.conteudo.pessoas.find((pessoa) => {
        return (pessoa.tipo == "Pretendente");
    });
}

function incluirCheckboxDoPretendenteNaLista(listaDePretendentes, idDaFamiliaEPretendente) {
    let itemDaLista = document.createElement("li");
    let checkboxDoPretendenteDaFamilia = document.createElement("input");
    checkboxDoPretendenteDaFamilia.setAttribute("id", `checkbox-da-familia-${idDaFamiliaEPretendente.id}`);
    checkboxDoPretendenteDaFamilia.setAttribute("type", "checkbox");

    let labelDoCheckbox = document.createElement("label");
    labelDoCheckbox.setAttribute("for", `checkbox-da-familia-${idDaFamiliaEPretendente.id}`);
    labelDoCheckbox.innerHTML = idDaFamiliaEPretendente.pretendente.nome;
            
    itemDaLista.appendChild(checkboxDoPretendenteDaFamilia);
    itemDaLista.appendChild(labelDoCheckbox);
    listaDePretendentes.appendChild(itemDaLista);
}

function realizarProcessoDeSelecao() {
    let idsDasFamiliasQueFaraoOProcessoDeSelecao = obterIdsDasFamiliasEscolhidasParaOProcesso();
    axios.put('http://localhost:8080/api/familia', {idsDasFamilias: idsDasFamiliasQueFaraoOProcessoDeSelecao})
    .then((resposta) => {
        window.alert('Processo de seleção realizado com sucesso!');
    })
    .catch((erro) => {
        window.alert('Erro ao realizar o processo de seleção.');
    });
}

function obterIdsDasFamiliasEscolhidasParaOProcesso() {
    let idsDasFamiliasQueFaraoOProcessoDeSelecao = []
    let checkboxesDasFamiliasEscolhidas = document.querySelectorAll('input[type=checkbox]:checked');
    checkboxesDasFamiliasEscolhidas.forEach(checkbox => {
       labelDaCheckbox = document.querySelector(`label[for=${checkbox.id}]`);
       let nomeDoPretendenteDaFamilia = labelDaCheckbox.innerHTML;
       let idDaFamilia = idsDasFamiliasESeusPretendentes.find((idDaFamiliaESeuPretendente) => {
        return nomeDoPretendenteDaFamilia == idDaFamiliaESeuPretendente.pretendente.nome;
    }).id;
       idsDasFamiliasQueFaraoOProcessoDeSelecao.push(idDaFamilia);
    });
    return idsDasFamiliasQueFaraoOProcessoDeSelecao;
}