
function cadastrarMaisUmaPessoaDaFamilia() {
    const novoFormulario = document.querySelector('.formulario-de-pessoa-da-familia').cloneNode(true);
    document.querySelector('.informacoes-da-familia').appendChild(novoFormulario);
}

function enviarCadastroDeFamilia() {
    const formulariosDePessoas = Array.from(document.querySelectorAll('.formulario-de-pessoa-da-familia'));
    let listaDeDadosDasPessoasDaFamilia = formulariosDePessoas.map(formularioDaPessoa => obterJsonDosDadosDaPessoa(formularioDaPessoa));
}

function obterJsonDosDadosDaPessoa(formularioDaPessoa) {
    const elementosDoFormularioDaPessoa = formularioDaPessoa.elements;
    return [].reduce.call(elementosDoFormularioDaPessoa, (dados, elemento) => {
        dados[elemento.dataset.js] = elemento.value;
        return dados;
    }, {});
}