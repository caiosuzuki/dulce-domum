package com.dulcedomum;

import com.dulcedomum.properties.LeitorDeArquivoProperties;
import com.dulcedomum.rest.MapeadorDeJsonJaxRs;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import java.util.Properties;
import java.util.Set;

@Configuration
@EntityScan(basePackageClasses = {Application.class})
@ComponentScan(basePackageClasses = {Application.class})
public class SpringBootConfiguracao {

    // TODO: checar a necessidade de configurar o ambiente, já que estou rodando apenas local e teste
    @Bean
    ConfiguracaoDoAmbiente configuracaoDoAmbiente() {
        Properties properties = new LeitorDeArquivoProperties("application.properties").ler();
        String ambiente = properties.getProperty("spring.profiles.active");
        Properties propriedadesDoAmbiente = new LeitorDeArquivoProperties("application-" + ambiente + ".properties").ler();
        ConfiguracaoDoAmbiente configuracaoDoAmbiente = new ConfiguracaoDoAmbiente(propriedadesDoAmbiente);
        ConfiguracaoDoAmbiente.definirConfiguracaoCorrente(configuracaoDoAmbiente);
        return configuracaoDoAmbiente;
    }

    // TODO: caso a configuração do ambiente seja descartada, essa anotação de DependsOn também sai
    @DependsOn("configuracaoDoAmbiente")
    @Component
    @ApplicationPath("/api")
    public class ConfigRestResources extends ResourceConfig {
        public ConfigRestResources() {
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(Boolean.TRUE);
            scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
            Set<BeanDefinition> beans = scanner.findCandidateComponents("com.dulcedomum.apresentacao.recurso");
            for (BeanDefinition beanDefinition : beans) {
                try {
                    this.registerClasses(Class.forName(beanDefinition.getBeanClassName()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            register(MapeadorDeJsonJaxRs.class);
        }
    }
}
