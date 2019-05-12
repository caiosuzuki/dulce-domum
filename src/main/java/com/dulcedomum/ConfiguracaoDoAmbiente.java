package com.dulcedomum;

import java.util.Properties;

public class ConfiguracaoDoAmbiente {


    private Properties configuracoes;

    private static ConfiguracaoDoAmbiente configuracaoCorrenteDoAmbiente;

    public ConfiguracaoDoAmbiente(Properties configuracoes) {
        this.configuracoes = configuracoes;
    }

    public static void definirConfiguracaoCorrente(ConfiguracaoDoAmbiente configuracaoDoAmbiente) {
        configuracaoCorrenteDoAmbiente = configuracaoDoAmbiente;
    }

    private static ConfiguracaoDoAmbiente corrente() {
        if (configuracaoCorrenteDoAmbiente == null) {
            throw new IllegalStateException("As configurações do ambiente não foram definidas.");
        }
        return configuracaoCorrenteDoAmbiente;
    }
}