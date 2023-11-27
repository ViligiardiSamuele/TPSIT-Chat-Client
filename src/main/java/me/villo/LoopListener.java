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
        System.out.println("[LoopListener] Avviato");
        String[] msg;
        String[] clients;
        int numClients;
        while (connessione.getSocket().isConnected()) {
            try {
                // messaggio ricevuto dal server
                msg = (connessione.getIn().readLine()).split(":");
                System.out.println("[LoopListener] Riceve [" + msg[0] + ":" + msg[1] + "]");
                if (msg[0].equals(ProtocolCodes.MSG.toString())) {
                    // Stampa messaggio da un utente
                    System.out.println("Messaggio da " + msg[1]);
                } else if (msg[0].equals(ProtocolCodes.MSG_RECIVED_BROADCAST.toString())) {
                    // Stampa messaggio broadcast
                    System.out.print("Messaggio Broadcast: ");
                    System.out.println(msg[1]);
                } else if (msg[0].equals(ProtocolCodes.USER_LIST.toString())) {
                    // Stampa lista client collegati

                    numClients = Integer.parseInt(msg[1].split(";")[0]);
                    msg[1] = (msg[1].split(";", 2))[1];
                    System.out.println("msg[1]" + msg[1]);
                    clients = msg[1].split(";", numClients);

                    System.out.println("--- Client collegati " + numClients + "---");
                    for (String client : clients) {
                        if (client.equals(connessione.getUtente().getNome()))
                            System.out.println(". " + client + " (Tu)");
                        else
                            System.out.println(". " + client);
                    }
                    System.out.println("----------------------------");
                } else {
                    // scrive sul buffer
                    do {
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
}