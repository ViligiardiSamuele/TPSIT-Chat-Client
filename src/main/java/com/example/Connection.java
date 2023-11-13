package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {
    private Socket s;
    private BufferedReader in;
    private DataOutputStream out;

    public Connection(Socket s) {
        this.s = s;
        try {
            this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.out = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {
            System.out.println("Errore istanza Connessione: " + e.getMessage());
        }
    }

    public BufferedReader getIn() {
        return this.in;
    }

    public DataOutputStream getOut() {
        return this.out;
    }

    public void close() {
        try {
            s.close();
        } catch (Exception e) {
        }
    }

    public String compose(String cmd, String data) {
        return cmd + ":" + data;
    }

    public String[] deCompose(String s) {
        return s.split(":");
    }
}
