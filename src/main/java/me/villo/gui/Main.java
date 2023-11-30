package me.villo.gui;

import java.io.IOException;

import me.villo.Connessione;
import me.villo.Utente;

public class Main {

    private static me.villo.Utente utente;

    public static void main(String[] args) {
        try {
            utente = new Utente(new Connessione("localhost", 2750));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new JF_Main();
    }

    public static me.villo.Utente getUtente() {
        return utente;
    }
}