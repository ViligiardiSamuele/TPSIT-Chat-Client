package me.villo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextArea;

import me.villo.gui.Main;

/**
 * <p>
 * Rimane costantemente in ascolto sulla connessione
 * e memorizza tutto sul buffer di quest'ultima
 * </p>
 * 
 * @author Viligiardi Samuele
 */
public class DaemonReader extends Thread {

    private Connessione connessione;

    /** JTextArea di Output */
    private JTextArea JTA_broadcast, JTA_private;

    /** Specifica dove direzionare le stampe */

    /** Risposta dal server */
    private String[] msg;

    /**
     * 
     * @param JTA_broadcast {@link JTextArea} per chat di broadcast
     * @param JTA_private   {@link JTextArea} per chat privata
     */
    public DaemonReader() {
        this.connessione = Main.getConnessione();
        this.JTA_broadcast = Main.getJF_Main().getJP_chatArea().getJTA_broadcast();
        this.JTA_private = Main.getJF_Main().getJP_chatArea().getJTA_private();
    }

    /**
     * <p>
     * Avvia LoopListener
     * </p>
     */
    @Override
    public void run() {
        String[] temp_Array;
        int n;
        while (connessione.getSocket().isConnected()) {
            try {
                // messaggio ricevuto dal server
                msg = (connessione.getIn().readLine()).split(":", 2);
                if (msg[0].equals(ProtocolCodes.MSG.toString())) {
                    // Stampa messaggio da un utente
                    print(getTime() + msg[1]);
                } else if (msg[0].equals(ProtocolCodes.MSG_RECIVED_BROADCAST.toString())) {
                    // Stampa messaggio broadcast
                    print(getTime() + msg[1]);
                } else if (msg[0].equals(ProtocolCodes.USER_LIST.toString())) {
                    /**
                     * Stampa lista client collegati
                     * Esempio di una stringa inviata dal server:
                     * "user_list : 2; mario; pippo;"
                     * 2 = numero di utenti contenuti
                     */
                    n = Integer.parseInt(msg[1].split(";")[0]); // estraggo il numero
                    msg[1] = (msg[1].split(";", 2))[1]; // estraggo gli utenti
                    temp_Array = msg[1].split(";", n); // eseguo split n volte per ogni utente

                    connessione.setUtentiOnline(temp_Array); // !!--> serve per la GUI
                    connessione.utentiOnlineUpdate = true;

                    Main.getJF_Main().getJP_userList().getJL_utentiOnline().removeAll();

                    for (int i = 0; i < temp_Array.length; i++)
                        if (Main.getUtente().getNome().equals(temp_Array[i]))
                            temp_Array[i] += " (Tu)";

                    // inserisce nella JList
                    Main.getJF_Main().getJP_userList().getJL_utentiOnline().setListData(temp_Array);

                } else if (msg[0].equals(ProtocolCodes.CHAT_DATA.toString())) {
                    // stampa lo storico della chat ricevuta dal server

                    n = Integer.parseInt(msg[1].split(";")[0]); // estraggo il numero
                    msg[1] = (msg[1].split(";", 2))[1]; // estraggo i messaggi
                    temp_Array = msg[1].split(";", n); // eseguo split n volte per ogni messaggio

                    for (String messaggio : temp_Array) {
                        print(messaggio);
                    }
                } else if (msg[0].equals(ProtocolCodes.BYE.toString())) {
                    System.out.println("Connessione in chiusura (richiesto dal server)");
                    connessione.close();
                    Thread.currentThread().interrupt();
                } else {
                    // scrive sul buffer
                    do {
                        // scrive solo se il buffer è vuoto
                        if (!connessione.lmfsHasValue) {
                            connessione.lasMsgFromServer = msg;
                            connessione.lmfsHasValue = true;
                            break;
                        }
                    } while (connessione.lmfsHasValue);
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * @param messaggio messaggio da stampare
     */
    private void print(String messaggio) {
        if (msg[0].equals(ProtocolCodes.MSG_RECIVED_BROADCAST.toString())) {
            JTA_broadcast.setText(JTA_broadcast.getText() + messaggio + "\n");
        } else {
            JTA_private.setText(JTA_private.getText() + messaggio + "\n");
        }
    }

    public void setConnessione(Connessione connessione) {
        this.connessione = connessione;
    }

    public void setJTA_broadcast(JTextArea jTA_broadcast, JTextArea jTA_private) {
        JTA_broadcast = jTA_broadcast;
        JTA_private = jTA_private;
    }

    /**
     * @return {@link String} con [HH:mm:ss]
     */
    public String getTime() {
        return "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")) + "]";
    }
}