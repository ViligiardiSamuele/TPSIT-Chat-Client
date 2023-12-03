package me.villo.gui;

import java.io.IOException;

import me.villo.Connessione;
import me.villo.DaemonReader;
import me.villo.Utente;
import me.villo.gui.frames.JD_Login;

public class Main {

    private static JF_Main JF_Main;
    private static me.villo.Utente utente;
    private static Connessione connessione;
    public static void main(String[] args) {
        JF_Main = new JF_Main();
        try {
            connessione = new Connessione("localhost", 2750);
            //connessione = new Connessione("79.26.135.88", 2750);
            connessione.setDaemonReader(new DaemonReader());
            utente = new Utente();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new JD_Login();
    }

    public static me.villo.Utente getUtente() {
        return utente;
    }

    public static JF_Main getJF_Main() {
        return JF_Main;
    }

    public static Connessione getConnessione() {
        return connessione;
    }

}