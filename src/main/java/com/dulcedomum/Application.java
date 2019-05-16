package com.dulcedomum;

import com.dulcedomum.eventodedominio.NotificadorDeEventoDeDominio;
import com.dulcedomum.eventodedominio.NotificadorDeEventoDeDominioPorSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Bean
    @Autowired
    public NotificadorDeEventoDeDominio notificadorDeEventoDeDominio(ApplicationContext applicationContext) {
        NotificadorDeEventoDeDominio notificadorDeEventoDeDominio = new NotificadorDeEventoDeDominioPorSpring(applicationContext);
        NotificadorDeEventoDeDominio.setNotificadorDeEventoDeDominioCorrente(notificadorDeEventoDeDominio);
        return NotificadorDeEventoDeDominio.getNotificadorCorrente();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
