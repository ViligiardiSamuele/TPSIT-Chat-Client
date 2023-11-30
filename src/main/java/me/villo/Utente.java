package me.villo;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Classe POJO Utente
 */
public class Utente {
    private String nome;

    /** {@link Connessione} singola per ogni utente */
    private Connessione connessione;

    /** {@link ActionManager} */
    private ActionManager actionManager;

    /**
     * 
     * @param nome  Nome utente
     * @param ip    IP server
     * @param porta Porta server
     * @throws UnknownHostException
     * @throws IOException
     */
    public Utente(String nome, String ip, int porta) throws UnknownHostException, IOException {
        this.nome = nome;
        this.connessione = new Connessione(ip, porta);
        actionManager = new ActionManager(connessione);
        connessione.sendCmdValue("hello", nome);
    }

    /**
     * 
     * @param ip    IP server
     * @param porta Porta server
     * @throws UnknownHostException
     * @throws IOException
     */
    public Utente(String ip, int porta) throws UnknownHostException, IOException {
        this.connessione = new Connessione(ip, porta);
        actionManager = new ActionManager(connessione);
    }

    /**
     * 
     * @param nome        Nome utente
     * @param connessione {@link Connessione} dell'utente
     * @throws UnknownHostException
     * @throws IOException
     */
    public Utente(String nome, Connessione connessione) throws UnknownHostException, IOException {
        this.nome = nome;
        this.connessione = connessione;
        actionManager = new ActionManager(connessione);
        connessione.sendCmdValue("hello", nome);
    }

    /**
     * 
     * @param connessione {@link Connessione} dell'utente
     * @throws UnknownHostException
     * @throws IOException
     */
    public Utente(Connessione connessione) throws UnknownHostException, IOException {
        this.connessione = connessione;
        actionManager = new ActionManager(connessione);
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public String getNome() {
        return nome;
    }

    public Connessione getConnessione() {
        return connessione;
    }

    /**
     * Modifica il nome dell'utente
     * 
     * @param nome Nome dell'utente
     */
    public void setNome(String nome) {
        this.nome = nome;
        // invia il nome al server
        connessione.sendCmdValue("hello", nome);
    }
}