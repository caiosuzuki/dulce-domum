package com.dulcedomum.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LeitorDeArquivoProperties {
    private final String nomeDoArquivo;

    public LeitorDeArquivoProperties(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }

    public Properties ler() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.nomeDoArquivo);
        if (inputStream == null) {
            throw new IllegalArgumentException("O arquivo properties " + this.nomeDoArquivo + " não foi encontrado.");
        } else {
            Properties properties = new Properties();

            try {
                properties.load(inputStream);
                return properties;
            } catch (IOException var4) {
                throw new IllegalStateException("Ocorreu um problema ao carregar as propriedades do arquivo " + this.nomeDoArquivo + ".");
            }
        }
    }
}