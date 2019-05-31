package com.dulcedomum;

import com.dulcedomum.apresentacao.restbase.MapeadorDeJsonJaxRs;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import java.util.Set;

@Configuration
@ComponentScan(basePackageClasses = {Application.class})
public class SpringBootConfiguracao {

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
