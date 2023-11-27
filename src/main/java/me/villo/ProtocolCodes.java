package me.villo;

public enum ProtocolCodes {
    /**
     * Invia un messaggio con il nome al server
     */
    HELLO("hello"),
    /**
     * Richiesta chiusura connessione al server
     */
    BYE("bye"),
    /**
     * Richiedi la lista degli utenti online al server
     */
    USERS_LIST_REQUEST("userListRequest"),
    /**
     * Lista utenti online inviata dal server
     */
    USER_LIST("userList"),
    /**
     * Notifica al server l'intenzione di inviare messaggi in broadcast
     */
    SWITCH_BROADCAST("switchBroadcast"),
    /**
     * Notifica al server l'intenzione di inviare messaggi ad un utente
     */
    SWITCH_TO_USER("switchToUser"),
    /**
     * Richiesta del server al client di inviare il messaggio
     */
    MSG_REQUEST("msgRequest"),
    /**
     * Messaggio
     */
    MSG("msg"),
    /**
     * Messaggio broadcast ricevuto dal client
     */
    MSG_RECIVED_BROADCAST("msgRecivedBroadcast");

    private final String cmd;

    ProtocolCodes(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return cmd;
    }

}