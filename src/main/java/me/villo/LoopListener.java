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
     * @param connessione {@link Connessione} dell'utente
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
        String[] clients;
        int numClients;
        while (connessione.getSocket().isConnected()) {
            try {
                // messaggio ricevuto dal server
                msg = (connessione.getIn().readLine()).split(":");
                if (msg[0].equals(ProtocolCodes.MSG.toString())) {
                    // Stampa messaggio da un utente
                    System.out.println("Messaggio da " + msg[1]);
                } else if (msg[0].equals(ProtocolCodes.MSG_RECIVED_BROADCAST.toString())) {
                    // Stampa messaggio broadcast
                    System.out.print("Messaggio Broadcast: ");
                    System.out.println(msg[1]);
                } else if (msg[0].equals(ProtocolCodes.USER_LIST.toString())) {
                    /**
                     * Stampa lista client collegati
                     * Esempio di una stringa inviata dal server:
                     * "user_list : 2; mario; pippo;"
                     * 2 = numero di utenti contenuti
                     */
                    numClients = Integer.parseInt(msg[1].split(";")[0]); // estraggo il numero
                    msg[1] = (msg[1].split(";", 2))[1]; // estraggo gli utenti
                    clients = msg[1].split(";", numClients); // eseguo split n volte per ogni utente

                    System.out.println("--- Client collegati [" + numClients + "] ---");
                    for (String client : clients) {
                        System.out.print("- " + client);
                        if (client.equals(connessione.getUtente().getNome()))
                            System.out.print(" (Tu)");
                        System.out.println();
                    }
                    System.out.println("----------------------------");
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
}