package com.dulcedomum.eventodedominiobase;

public interface ManipuladorDeEventoDeDominio<Evento extends EventoDeDominio> {
    void manipular(Evento evento);
}
