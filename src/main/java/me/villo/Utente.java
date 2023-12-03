package me.villo;

import me.villo.gui.Main;

/**
 * Classe POJO Utente
 * @author Viligiardi Samuele
 */
public class Utente {
    private String nome;
    
    public Utente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        // invia il nome al server
        Main.getConnessione().sendCmdValue("hello", nome);
    }
}