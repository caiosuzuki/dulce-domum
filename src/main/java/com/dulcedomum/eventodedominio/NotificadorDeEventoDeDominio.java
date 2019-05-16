package com.dulcedomum.eventodedominio;

public abstract class NotificadorDeEventoDeDominio {
    private static NotificadorDeEventoDeDominio notificadorDeEventoDeDominioCorrente;

    public NotificadorDeEventoDeDominio() {
    }

    public abstract <Evento extends EventoDeDominio> void notificarSobre(Evento evento);

    public static NotificadorDeEventoDeDominio getNotificadorCorrente() {
        return notificadorDeEventoDeDominioCorrente;
    }

    public static void setNotificadorDeEventoDeDominioCorrente(NotificadorDeEventoDeDominio notificadorDeEventoDeDominio) {
        notificadorDeEventoDeDominioCorrente = notificadorDeEventoDeDominio;
    }
}
