package me.villo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Classe per gestire la connessione
 */
public class Connessione {
    private Socket socket;
    private BufferedReader in;
    private DataOutputStream out;
    private Utente utente;

    /**
     * Buffer su cui il LoopListener
     * scrive tutte le risposte dal server
     */
    protected String[] lasMsgFromServer;
    protected Boolean lmfsHasValue; //LastMessageFromServerHasValue

    private LoopListener loopListener;

    /**
     * Connessione
     * 
     * @param ip     L'Ip del server
     * @param porta  La porta del server
     * @param utente Utente associato
     */
    public Connessione(String ip, int porta, Utente utente) {
        try {
            this.socket = new Socket(ip, porta);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.utente = utente;
        lmfsHasValue = false;
        loopListener = new LoopListener(this);
        loopListener.start(); // Avvio LoopListener
    }

    /**
     * Notifica al server l'intezione di chiudere la connessione
     * <ol>
     * <li>Interrompe il LoopListener</li>
     * <li>Chiude "BufferedReader in"</li>
     * <li>Chiude "DataOutputStream out"</li>
     * <li>Chiude la Socket</li>
     * </ol>
     */
    public void close() {
        try {
            sendCmdValue(ProtocolCodes.BYE.toString(), "1");
            loopListener.interrupt();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invia un messaggio al server
     * 
     * @param cmd   da {@link ProtocolCodes}
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

    public String[] getLasMsgFromServer() {
        return lasMsgFromServer;
    }

    public Utente getUtente() {
        return utente;
    }
}