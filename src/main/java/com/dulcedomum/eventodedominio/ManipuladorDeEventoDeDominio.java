package com.dulcedomum.eventodedominio;

public interface ManipuladorDeEventoDeDominio<Evento extends EventoDeDominio> {
    void manipular(Evento evento);
}
