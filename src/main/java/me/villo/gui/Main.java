package me.villo.gui;

import java.io.IOException;

import me.villo.Connessione;
import me.villo.Utente;

public class Main {

    private static me.villo.Utente utente;
    private static JF_Main JF_Main;

    public static void main(String[] args) {
        try {
            utente = new Utente(new Connessione("localhost", 2750));
            // utente = new Utente(new Connessione("79.26.135.88", 2750));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JF_Main = new JF_Main();
    }

    public static me.villo.Utente getUtente() {
        return utente;
    }

    public static JF_Main getJF_Main() {
        return JF_Main;
    }

}