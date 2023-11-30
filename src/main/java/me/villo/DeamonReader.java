package me.villo;

import javax.swing.JTextArea;

import me.villo.gui.Main;

/**
 * <p>
 * Rimane costantemente in ascolto sulla connessione
 * e memorizza tutto sul buffer di quest'ultima
 * </p>
 */
public class DeamonReader extends Thread {

    private Connessione connessione;

    /** JTextArea di Output */
    private JTextArea JTA_broadcast, JTA_private;

    /** Specifica dove direzionare le stampe */
    private Boolean useJTAouts;

    /** Risposta dal server */
    private String[] msg;

    public DeamonReader() {
        this.connessione = Main.getUtente().getConnessione();
        this.useJTAouts = false;
    }

    /**
     * 
     * @param JTA_broadcast {@link JTextArea} per chat di broadcast
     * @param JTA_private   {@link JTextArea} per chat privata
     */
    public DeamonReader(JTextArea JTA_broadcast, JTextArea JTA_private) {
        this.connessione = Main.getUtente().getConnessione();
        this.JTA_broadcast = JTA_broadcast;
        this.JTA_private = JTA_private;
        this.useJTAouts = true;
    }

    /**
     * <p>
     * Avvia LoopListener
     * </p>
     */
    @Override
    public void run() {
        String[] clients;
        int nClients;
        while (connessione.getSocket().isConnected()) {
            try {
                // messaggio ricevuto dal server
                msg = (connessione.getIn().readLine()).split(":");
                if (msg[0].equals(ProtocolCodes.MSG.toString())) {
                    // Stampa messaggio da un utente
                    print("Messaggio da " + msg[1]);
                } else if (msg[0].equals(ProtocolCodes.MSG_RECIVED_BROADCAST.toString())) {
                    // Stampa messaggio broadcast
                    print("Messaggio Broadcast: " + msg[1]);
                } else if (msg[0].equals(ProtocolCodes.USER_LIST.toString())) {
                    /**
                     * Stampa lista client collegati
                     * Esempio di una stringa inviata dal server:
                     * "user_list : 2; mario; pippo;"
                     * 2 = numero di utenti contenuti
                     */
                    nClients = Integer.parseInt(msg[1].split(";")[0]); // estraggo il numero
                    msg[1] = (msg[1].split(";", 2))[1]; // estraggo gli utenti
                    clients = msg[1].split(";", nClients); // eseguo split n volte per ogni utente
                    
                    connessione.setUtentiOnline(clients); //!!--> serve per la GUI

                    StringBuilder sb = new StringBuilder();
                    sb.append("--- Client collegati [" + nClients + "] ---");
                    for (String client : clients) {
                        sb.append("- " + client);
                        if (client.equals(Main.getUtente().getNome()))
                            sb.append(" (Tu)");
                        sb.append("\n");
                    }
                    sb.append("----------------------------");
                    print(sb.toString());
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
     * <p>
     * Stampa il messaggio in base al tipo di utilizzo
     * </p>
     * <ul>
     * <li>GUI</li>
     * <li>CLI</li>
     * </ul>
     * ------
     * 
     * @param messaggio messaggio da stampare
     */
    private void print(String messaggio) {
        if (useJTAouts) {
            if (msg[0].equals(ProtocolCodes.MSG_RECIVED_BROADCAST.toString())) {
                JTA_broadcast.setText(JTA_broadcast.getText() + messaggio);
            } else {
                JTA_private.setText(JTA_private.getText() + messaggio);
            }
        } else {
            System.out.println(messaggio);
        }
    }

    public void setConnessione(Connessione connessione) {
        this.connessione = connessione;
    }

    public void setJTA_broadcast(JTextArea jTA_broadcast, JTextArea jTA_private) {
        JTA_broadcast = jTA_broadcast;
        JTA_private = jTA_private;
        this.useJTAouts = true;
    }

    public void setUseJTAouts(Boolean useJTAouts) {
        this.useJTAouts = useJTAouts;
    }
}