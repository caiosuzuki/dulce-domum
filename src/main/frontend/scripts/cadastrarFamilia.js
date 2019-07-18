function cadastrarMaisUmaPessoaDaFamilia() {
    const novoFormulario = document.querySelector('.formulario-de-pessoa-da-familia').cloneNode(true);
    document.querySelector('.informacoes-da-familia').appendChild(novoFormulario);
}

function enviarCadastroDeFamilia() {
    const formulariosDePessoas = Array.from(document.querySelectorAll('.formulario-de-pessoa-da-familia'));
    const listaDeDadosDasPessoasDaFamilia = formulariosDePessoas.map(formularioDaPessoa => obterJsonDosDadosDaPessoa(formularioDaPessoa));
    const statusDaFamilia = document.querySelector('[data-js="statusDaFamilia"]').value;
    const dadosParaCadastroDaFamilia = Object.assign({}, {pessoasDaFamilia: listaDeDadosDasPessoasDaFamilia}, {status: statusDaFamilia});
    // TODO: requisição POST em /api/familia passando dadosParaCadastroDaFamilia em JSON
}

function obterJsonDosDadosDaPessoa(formularioDaPessoa) {
    const elementosDoFormularioDaPessoa = formularioDaPessoa.elements;
    return [].reduce.call(elementosDoFormularioDaPessoa, (dados, elemento) => {
        dados[elemento.dataset.js] = elemento.value;
        return dados;
    }, {});
}