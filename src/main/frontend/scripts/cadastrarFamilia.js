function cadastrarMaisUmaPessoaDaFamilia() {
    const novoFormulario = document.querySelector('.formulario-de-pessoa-da-familia').cloneNode(true);
    document.querySelector('.informacoes-da-familia').appendChild(novoFormulario);
}

function enviarCadastroDeFamilia() {
    const formulariosDePessoas = Array.from(document.querySelectorAll('.formulario-de-pessoa-da-familia'));
    const listaDeDadosDasPessoasDaFamilia = formulariosDePessoas.map(formularioDaPessoa => obterJsonDosDadosDaPessoa(formularioDaPessoa));
    const statusDaFamilia = document.querySelector('[data-js="statusDaFamilia"]').value;
    const dadosParaCadastroDaFamilia = Object.assign({}, {pessoasDaFamilia: listaDeDadosDasPessoasDaFamilia}, {status: statusDaFamilia});

    axios.post('http://localhost:8080/api/familia', dadosParaCadastroDaFamilia)
    .then((resposta) => {
        window.alert('Família adicionada com sucesso!', resposta);
    })
    .catch((erro) => {
        window.alert('Ocorreu um erro ao cadastrar a família. ', erro);
    });
    
}

response.setHeader("Access-Control-Allow-Origin", "*");
response.setHeader("Access-Control-Allow-Credentials", "true");
response.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

function obterJsonDosDadosDaPessoa(formularioDaPessoa) {
    const elementosDoFormularioDaPessoa = formularioDaPessoa.elements;
    return [].reduce.call(elementosDoFormularioDaPessoa, (dados, elemento) => {
        dados[elemento.dataset.js] = elemento.value;
        return dados;
    }, {});
}