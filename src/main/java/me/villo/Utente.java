package me.villo;

import java.io.IOException;
import java.net.UnknownHostException;

public class Utente {
    private String nome;
    private Connessione connessione;
    private ActionManager actionManager;

    public Utente(String nome, String ip, int porta) throws UnknownHostException, IOException {
        this.nome = nome;
        this.connessione = new Connessione(ip, porta);
        actionManager = new ActionManager(connessione);
        connessione.sendCmdValue("hello", nome);
    }

    public Utente(String ip, int porta) throws UnknownHostException, IOException {
        this.connessione = new Connessione(ip, porta);
        actionManager = new ActionManager(connessione);
    }

    public Utente(String nome, Connessione connessione) throws UnknownHostException, IOException {
        this.nome = nome;
        this.connessione = connessione;
        actionManager = new ActionManager(connessione);
        connessione.sendCmdValue("hello", nome);
    }

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

    public void setNome(String nome) {
        this.nome = nome;
        //invia il nome al server
        connessione.sendCmdValue("hello", nome);
    }

}
