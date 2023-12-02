package me.villo;

/**
 * Fornisce una lista dei comandi da inviare al server
 */
public interface ProtocolCodes {
    /**
     * Invia un messaggio con il nome al server
     */
    String HELLO = "hello";
    /**
     * Richiesta chiusura connessione al server
     */
    String BYE = "bye";
    /**
     * Richiedi la lista degli utenti online al server
     */
    String USERS_LIST_REQUEST = "userListRequest";
    /**
     * Lista utenti online inviata dal server
     */
    String USER_LIST = "userList";
    /**
     * Notifica al server l'intenzione di inviare messaggi in broadcast
     */
    String SWITCH_BROADCAST = "switchBroadcast";
    /**
     * Notifica al server l'intenzione di inviare messaggi ad un utente
     */
    String SWITCH_TO_USER = "switchToUser";
    /**
     * Richiesta del server al client di inviare il messaggio
     */
    String MSG_REQUEST = "msgRequest";
    /**
     * Messaggio
     */
    String MSG = "msg";
    /**
     * Messaggio broadcast ricevuto dal client
     */
    String MSG_RECIVED_BROADCAST = "msgRecivedBroadcast";
    /**
     * Messaggio broadcast ricevuto dal client
     */
    String CHAT_REQUEST = "chatRequest";
    /**
     * Messaggio broadcast ricevuto dal client
     */
    String CHAT_DATA = "chatData";
}