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

    /** input keyboard utente */
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
                    System.out.println("/user              -> Scrivi un messaggio ad un utente");
                    System.out.println("/bc                -> Scrivi un messaggio a tutti (broadcast)");
                    System.out.println("/exit              -> Chiudi la connessione");
                    System.out.println("Scrivi per inviare un messaggio nel canase selezionato");
                    break;
                case "/list":
                    connessione.sendCmdValue(ProtocolCodes.USERS_LIST_REQUEST.toString(), "1");
                    break;
                case "/user":
                    if (chechParametro()) {
                        System.out.println("Parametro mancante");
                        break;
                    }
                    connessione.sendCmdValue(ProtocolCodes.SWITCH_TO_USER.toString(), kbInput[1]);
                    checkMsgRequest();
                    canaleSelezionato = true;
                    break;
                case "/bc":
                    connessione.sendCmdValue(ProtocolCodes.SWITCH_BROADCAST.toString(), "1");
                    checkMsgRequest();
                    canaleSelezionato = true;
                    break;
                case "/exit":
                    connessione.close();
                    break;
            }
        } else {
            if (!canaleSelezionato) {
                System.out.println("!-> Non e' stato selezionato nessun canale <-!");
                return;
            }
            if (cmd.trim().equals("")) {
                System.out.println("!-> Input invalido <-!");
                return;
            } else {
                // Invio messaggio
                connessione.sendCmdValue(ProtocolCodes.MSG.toString(), cmd);
            }
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
            return kbInput[1].trim().equals("");
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
        return command.trim().split("\\s+");
    }

    /**
     * <p>
     * Verifica che il server abbia ricevuto il cambio canale
     * </p>
     */
    public void checkMsgRequest() {
        System.out.println("Richiesta inviata...");
        do {
            try {
                /**
                 * Aspetta 50 ms per consentire a
                 * LoopListener di aggiornare il buffer
                 * prima di ritentare
                 */
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            if (connessione.lmfsHasValue) {
                if (connessione.lasMsgFromServer[0].equals(ProtocolCodes.MSG_REQUEST.toString())) {
                    System.out.println("Canale cambiato");
                    connessione.lmfsHasValue = false;
                    return;
                }
            }
        } while (!connessione.lmfsHasValue);
    }
}