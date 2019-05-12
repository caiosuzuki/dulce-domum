package com.dulcedomum.base;

import com.dulcedomum.rest.MapeadorRestDeObjetos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class ConfiguracaoDeMapeadorDeObjetosParaTeste {
    @Bean
    public ObjectMapper customizarJson() {
        return MapeadorRestDeObjetos.criar();
    }
}