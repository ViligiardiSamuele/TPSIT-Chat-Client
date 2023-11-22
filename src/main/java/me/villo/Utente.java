package me.villo;

public class Utente {
    private String nome;
    private Connessione connessione;
    private ActionManager actionManager;

    public Utente(String nome, Connessione connessione) {
        this.nome = nome;
        this.connessione = connessione;
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
