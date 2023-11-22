package me.villo;

/**
 * <p>
 * Gestisce l'input da tastiera di un utente
 * </p>
 */
public class ActionManager {
    /**
     * Verifica se il canale è stato selezionato
     * prima di inviare un messaggio
     */
    private Boolean canaleSelezionato;

    private Connessione connessione;

    /** input tastiera utente */
    private String[] kbInput;

    /**
     * <p>
     * Costruttore
     * </p>
     * 
     * @param connessione Connessione dell'utente
     */
    public ActionManager(Connessione connessione) {
        this.connessione = connessione;
        this.canaleSelezionato = false;
    }

    /**
     * <p>
     * Esegue il comando
     * </p>
     * 
     * @param cmd comando
     */
    public void esegui(String cmd) {
        if (cmd.startsWith("/")) {
            kbInput = deComposeConsoleCommand(cmd);
            switch (kbInput[0]) {
                case "/info":
                    System.out.println("/list              -> Visualizza gli utenti online");
                    System.out.println("/toUser            -> Scrivi un messaggio ad un utente");
                    // System.out.println("/toUsers -> Scrivi un messaggio a piu' utenti");
                    System.out.println("/broadcast         -> Scrivi un messaggio a tutti");
                    System.out.println("/exit              -> Chiudi la connessione");
                    System.out.println("Scrivi per inviare un messaggio nel canase selezionato");
                    break;
                case "/list":
                    connessione.sendCmdValue(ProtocolCodes.USERS_LIST_REQUEST.toString(), "1");
                    break;
                case "/toUser":
                    if (chechParametro())
                        break;
                    connessione.sendCmdValue(ProtocolCodes.SWITCH_TO_USER.toString(), kbInput[1]);
                    checkMsgRequest();
                    canaleSelezionato = true;
                    break;
                case "/broadcast":
                    connessione.sendCmdValue(ProtocolCodes.SWITCH_BROADCAST.toString(), "1");
                    checkMsgRequest();
                    canaleSelezionato = true;
                    break;
                case "/exit":
                    connessione.close();
            }
        } else {
            if (!canaleSelezionato) {
                System.out.println("!-> Non e' stato selezionato nessun canale <-!");
                return;
            }
            if (cmd.trim().equals("")) {
                System.out.println("!-> Input invalido <-!");
                return;
            } else
                // Invio messaggio
                connessione.sendCmdValue(ProtocolCodes.MSG.toString(), cmd);
        }
    }

    /**
     * <p>
     * Effettua controlli basici sull'input dell'utente
     * </p>
     * 
     * @return ckeck passato o no
     */
    private Boolean chechParametro() {
        try {
            return !kbInput[1].trim().equals("");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("!-> Parametro mancante <-!");
        } catch (Exception e) {
            System.out.println("!-> Errore nei parametri <-!");
        }
        return true;
    }

    /**
     * <p>
     * Separa il comando dal parametro
     * </p>
     * 
     * @param command Input utente
     * @return Comando suddiviso
     */
    private String[] deComposeConsoleCommand(String command) {
        return command.split("-");
    }

    /**
     * <p>
     * Verifica che il server abbia ricevuto il cambio canale
     * </p>
     */
    public void checkMsgRequest() {
        System.out.println("Aspetto conferma server...");
        do {
            //for(int i = 0; i < 1000; i++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (connessione.lmfsHasValue) {
                // System.out.println(messaggi.toString());
                if (connessione.lasMsgFromServer[0].equals(ProtocolCodes.MSG_REQUEST.toString())) {
                    System.out.println("Conferma ricevuta");
                    connessione.lmfsHasValue = false;
                    return;
                }
            }
        } while (!connessione.lmfsHasValue);
    }
}