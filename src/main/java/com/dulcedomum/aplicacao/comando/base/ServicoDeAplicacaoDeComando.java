package com.dulcedomum.aplicacao.comando.base;

public interface ServicoDeAplicacaoDeComando<C extends Comando> {
    ConfirmacaoDeSucesso executar(C comando);
}
