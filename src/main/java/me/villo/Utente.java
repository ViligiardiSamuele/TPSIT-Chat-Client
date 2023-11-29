package me.villo;

public class Utente {
    private String nome;
    private Connessione connessione;
    private ActionManager actionManager;

    public Utente(String nome, String ip, int porta) {
        this.nome = nome;
        this.connessione = new Connessione(ip, porta, this);
        actionManager = new ActionManager(connessione);
        connessione.sendCmdValue("hello", nome);
    }

    public Utente(String ip, int porta) {
        this.nome = "";
        this.connessione = new Connessione(ip, porta, this);
        actionManager = new ActionManager(connessione);
        connessione.sendCmdValue("hello", nome);
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

}
