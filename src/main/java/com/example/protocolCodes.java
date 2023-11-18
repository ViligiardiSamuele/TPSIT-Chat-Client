package com.example;

public enum protocolCodes {
    /**
     * <p>Invia un messaggio con il nome al server</p>
     */
    HELLO ("hello"),
    /**
     * <p>Richiesta chiusura connessione al server</p>
     */
    BYE ("bye"),
    /**
     * <p>Richiedi la lista degli utenti online al server</p>
     */
    USERS_LIST_REQUEST ("usersListRequest"),
    /**
     * <p>Lista utenti online inviata dal server</p>
     */
    USER_LIST ("usersList"),
    /**
     * <p>Notifica al server l'intenzione di inviare messaggi in broadcast</p>
     */
    SWITCH_BROADCAST ("switchBroadcast"),
    /**
     * <p>Notifica al server l'intenzione di inviare messaggi ad un utente</p>
     */
    SWITCH_TO_USER ("switchToUser"),
    /**
     * <p>Richiesta del server al client di inviare il messaggio</p>
     */
    MSG_REQUEST ("msgRequest"),
    /**
     * <p>Messaggio</p>
     */
    MSG ("msg"),
    /**
     * <p>Messaggio broadcast ricevuto dal client</p>
     */
    MSG_RECIVED_BROADCAST ("msgRecivedBroadcast");

    private final String cmd;
    protocolCodes (String cmd){
        this.cmd =  cmd;
    }

    @Override
    public String toString(){
        return cmd;
    }

}