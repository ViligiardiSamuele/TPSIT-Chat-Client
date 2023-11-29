package me.villo.gui;

import me.villo.Utente;

public class Main {

    private static me.villo.Utente utente;

    public static void main(String[] args) {
        utente = new Utente("localhost", 2750);

        new JF_Main();
    }

    public static me.villo.Utente getUtente() {
        return utente;
    }
    
}
