package me.villo;

/**
 * <p>
 * Rimane costantemente in ascolto sulla connessione
 * e memorizza tutto sul buffer di quest'ultima
 * </p>
 */
public class LoopListener extends Thread {
    private Connessione connessione;

    /**
     * <p>
     * Costruttore
     * </p>
     * 
     * @param connessione Connessione dell'utente
     */
    public LoopListener(Connessione connessione) {
        this.connessione = connessione;
    }

    /**
     * <p>
     * Avvia LoopListener
     * </p>
     */
    @Override
    public void run() {
        String[] msg;
        while (connessione.getSocket().isConnected()) {
            try {
                // messaggio ricevuto dal server
                msg = connessione.getIn().readLine().split(":");
                if (msg[0].equals(ProtocolCodes.MSG.toString())) {
                    // Stampa messaggio da un utente
                    System.out.println(msg[1]);
                } else if (msg[0].equals(ProtocolCodes.MSG_RECIVED_BROADCAST.toString())) {
                    // Stampa messaggio broadcast
                    System.out.print("Messaggio Broadcast: ");
                    System.out.println(msg[1]);
                } else {
                    // scrive sul buffer
                    do {
                        if (!connessione.lmfsHasValue) {
                            connessione.lasMsgFromServer = msg;
                            connessione.lmfsHasValue = true;
                        }
                    } while (connessione.lmfsHasValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}