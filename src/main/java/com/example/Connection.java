package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * <p>
 * Questa classe gestisce le connessioni socket con un server ed
 * offre metodi per la gestione di quest'ultima
 * </p>
 */
public class Connection extends Thread {
    private Socket s;
    private BufferedReader in;
    private DataOutputStream out;
    private String[] lastMsgFromServer;

    /**
     * <p>
     * Costruttore
     * </p>
     * 
     * @param s Socket
     */
    public Connection(Socket s) {
        this.s = s;
        try {
            this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.out = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {
            System.out.println("Errore istanza Connessione: " + e.getMessage());
        }
        lastMsgFromServer = new String[] {"msgRequest","1"};
    }

    @Override
    public void run() {
        String[] response;
        while (true) {
            response = receveKeyValue();
            if (response[0] == "msgRecivedBroadcast") {
                System.out.println("-----------------");
                System.out.print("Canale Broadcast: " + response[1]);
                System.out.println("-----------------");
            } else {
                lastMsgFromServer = response;
                System.out.println(lastMsgFromServer[0] + " : " + lastMsgFromServer[1]);
            }
        }
    }

    /**
     * @return la comunicazione in ricezione
     */
    public BufferedReader getIn() {
        return this.in;
    }

    /**
     * 
     * @return La comunicazione in uscita
     */
    public DataOutputStream getOut() {
        return this.out;
    }

    /**
     * <p>
     * Chiude la connessione inviando "bye:1" al server ed
     * invocando il metodo .close() sulla Socket
     * </p>
     */
    public void close() {
        try {
            out.writeBytes("bye:1" + "\n");
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * <p>
     * Invoca il metono compose() ed invia il pacchetto
     * </p>
     * 
     * @param key   Codice comando (es. msg)
     * @param value Valore accoppiato (es. "Hello")
     */
    public void sendKeyValue(String key, String value) {
        try {
            this.out.writeBytes(compose(key, value) + "\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * <p>
     * Rimane in ascolto su cosa invia il server
     * </p>
     * 
     * @return String[] con dentro 2 valori: "key","value"
     */
    private String[] receveKeyValue() {
        try {
            return deCompose(in.readLine());
        } catch (Exception e) {
            System.out.println(e);
        }
        return new String[] {};
    }

    /**
     * <p>
     * Compone il messaggio per essere inviato
     * </p>
     * 
     * @param cmd  Comando (es. "cmd")
     * @param data Valore aggiuntivo (es. "Hello")
     * @return Stringa composta
     */
    public String compose(String cmd, String data) {
        return cmd + ":" + data;
    }

    /**
     * <p>
     * </p>
     * 
     * @param s
     * @return
     */
    public String[] deCompose(String s) {
        return s.split(":");
    }

    public Socket getSocket() {
        return s;
    }

    public String[] getLastMsgFromServer() {
        return lastMsgFromServer;
    }

}