function irParaNovaHome() {
    let senhaDigitada = window.prompt('Digite a senha para acessar o produto: ');
    if (senhaDigitada == "senha123") {
        let usuarioQuerAbrirEmNovaAba = window.confirm('Deseja abrir em nova aba?');
        if (usuarioQuerAbrirEmNovaAba) {
            // window.open("http://127.0.0.1:61998/src/main/frontend/paginaInicial.html");
            window.open('paginaInicial.html');
        } else {
            window.location = 'paginaInicial.html';
        }
    } else {
        window.alert('Senha incorreta, tente novamente.')
    }
}