package com.dulcedomum.eventodedominio;

import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class NotificadorDeEventoDeDominioPorSpring extends NotificadorDeEventoDeDominio {

    private ApplicationContext applicationContext;

    public NotificadorDeEventoDeDominioPorSpring(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <E extends EventoDeDominio> void notificarSobre(E evento) {
        String[] beans = applicationContext.getBeanNamesForType(ManipuladorDeEventoDeDominio.class);
        List<Object> todosOsManipuladoresDeEventosDeDominio = Stream.of(beans).map(bean -> applicationContext.getBean(bean)).collect(toList());
        List<ManipuladorDeEventoDeDominio<E>> manipuladoresQueObservamOEventoDeDominio = buscarOsManipuladoresDoEventoDeDominio(evento, todosOsManipuladoresDeEventosDeDominio);
        manipuladoresQueObservamOEventoDeDominio.forEach(manipulador -> manipulador.manipular(evento));
    }

    private <E extends EventoDeDominio> List<ManipuladorDeEventoDeDominio<E>> buscarOsManipuladoresDoEventoDeDominio(E eventoDeDominio, List<Object> manipuladoresDeEventoDeDominio) {
        return manipuladoresDeEventoDeDominio.stream()
                .filter(manipulador -> manipuladorDeEventoDeDominioObservaOEvento((ManipuladorDeEventoDeDominio<?>) manipulador, eventoDeDominio))
                .map(manipulador -> (ManipuladorDeEventoDeDominio<E>) manipulador)
                .collect(toList());
    }

    private <E extends EventoDeDominio> boolean manipuladorDeEventoDeDominioObservaOEvento(ManipuladorDeEventoDeDominio<?> manipuladorDeEventoDeDominio, E eventoDeDominio) {
        Class classeDoManipuladorDeEventoDeDominio = manipuladorDeEventoDeDominio.getClass();
        for (Type interfaceQueOManipuladorImplementa : classeDoManipuladorDeEventoDeDominio.getGenericInterfaces()) {
            if (interfaceQueOManipuladorImplementa instanceof ParameterizedType) {
                return manipuladorDeEventoImplementaOEvento(interfaceQueOManipuladorImplementa, eventoDeDominio);
            }
        }
        return false;
    }

    private <E extends EventoDeDominio> boolean manipuladorDeEventoImplementaOEvento(Type interfaceQueOManipuladorImplementa, E eventoDeDominio) {
        ParameterizedType parameterizedType = (ParameterizedType) interfaceQueOManipuladorImplementa;
        return Stream.of(parameterizedType.getActualTypeArguments())
                .map(tipoDaClasseDoEventoDeDominio -> (Class<?>) tipoDaClasseDoEventoDeDominio)
                .anyMatch(classeDoEventoDeDominio -> classeDoEventoDeDominio.isInstance(eventoDeDominio));
    }
}
