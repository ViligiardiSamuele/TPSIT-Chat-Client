package me.villo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connessione {
    private Socket socket;
    private BufferedReader in;
    private DataOutputStream out;

    /**
     * Buffer su cui il LoopListener
     * scrive ogni messaggio che riceve
     */
    private List<String[]> buffer;

    private LoopListener loopListener;

    public Connessione(String ip, int porta) {
        try {
            this.socket = new Socket(ip, porta);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        buffer = new ArrayList<>();
        loopListener = new LoopListener(this);
        loopListener.start(); // Avvio LoopListener
    }

    /**
     * Notifica al server l'intezione di chiudere la connessione
     * <ol>
     * <li>Chiude BufferedReader in</li>
     * <li>Chiude DataOutputStream out</li>
     * <li>Chiude la Socket</li>
     * </ol>
     */
    public void close() {
        try {
            sendCmdValue(ProtocolCodes.BYE.toString(), "1");
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invia un messaggio al server
     * @param cmd   da ProtocolCodes
     * @param value inserito dall'utente
     */
    public void sendCmdValue(String cmd, String value) {
        try {
            out.writeBytes(cmd + ":" + value + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Socket getSocket() {
        return socket;
    }

    protected BufferedReader getIn() {
        return in;
    }

    public List<String[]> getBuffer() {
        return buffer;
    }

}