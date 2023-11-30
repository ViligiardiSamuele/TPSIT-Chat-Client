package me.villo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe per gestire la connessione
 */
public class Connessione {
    private Socket socket;
    private BufferedReader in;
    private DataOutputStream out;

    /** Lista di utenti online (aggiornata da DaemonReader) */
    private String[] utentiOnline;
    protected Boolean utentiOnlineUpdate;

    /**
     * Buffer su cui il LoopListener
     * scrive tutte le risposte dal server
     */
    protected String[] lasMsgFromServer = null;
    /** LastMessageFromServerHasValue */
    protected Boolean lmfsHasValue;

    private DaemonReader daemonReader = null;

    /**
     * Connessione
     * 
     * @param ip     L'Ip del server
     * @param porta  La porta del server
     * @param utente Utente associato
     * @throws IOException
     * @throws UnknownHostException
     */
    public Connessione(String ip, int porta) throws UnknownHostException, IOException {
        this.socket = new Socket(ip, porta);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new DataOutputStream(socket.getOutputStream());
        lmfsHasValue = false;
        utentiOnlineUpdate = false;
        utentiOnline = new String[] {};
    }

    public Connessione(String ip, int porta, DaemonReader daemonReader) throws UnknownHostException, IOException {
        this.socket = new Socket(ip, porta);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new DataOutputStream(socket.getOutputStream());
        lmfsHasValue = false;
        utentiOnlineUpdate = false;
        this.daemonReader = daemonReader;
        daemonReader.setDaemon(true);
        daemonReader.start();
        utentiOnline = new String[] {};
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
            sendCmdValue(ProtocolCodes.BYE, "1");
            daemonReader.interrupt();
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

    public Boolean checkNewValueOfLMFS() {
        do {
            try {
                /**
                 * Aspetta 50 ms per consentire a
                 * DaemonReader di aggiornare il buffer
                 * prima di ritentare
                 */
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            if (lmfsHasValue) {
                if (lasMsgFromServer[0].equals(ProtocolCodes.MSG_REQUEST)) {
                    lmfsHasValue = false;
                    return true;
                }
            }
        } while (!lmfsHasValue);
        return false;
    }

    public void setDaemonReader(DaemonReader daemonReader) {
        if (this.daemonReader != null)
            this.daemonReader.interrupt();
        this.daemonReader = daemonReader;
        this.daemonReader.setDaemon(true);
        this.daemonReader.start();
    }

    public void setUtentiOnline(String[] utentiOnline) {
        this.utentiOnline = utentiOnline;
    }

    public void updateUtentiOnline() {
        sendCmdValue(ProtocolCodes.USERS_LIST_REQUEST, "1");
        
        //attende l'aggiornamento
        do {
            try {
                /**
                 * Aspetta 50 ms per consentire a
                 * DaemonReader di aggiornare il buffer
                 * prima di ritentare
                 */
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            if (utentiOnlineUpdate) {
                if (lasMsgFromServer[0].equals(ProtocolCodes.USER_LIST)) {
                    utentiOnlineUpdate = false;
                }
            }
        } while (!utentiOnlineUpdate);
    }

    public String[] getUtentiOnline() {
        return utentiOnline;
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
}