package com.example.options;

import com.example.Utente;
import com.example.protocolCodes;

public class SendMsgAtUser extends Option {

    private String destinatario;
    private String msg;

    public SendMsgAtUser(Utente utente, String destinatario, String msg) {
        super(utente);
        this.destinatario = destinatario;
        this.msg = msg;
    }

    @Override
    public String execute() {
        getUtente().getConnection().sendKeyValue(protocolCodes.SWITCH_TO_USER.toString(), destinatario);
        String[] serverResponse = getUtente().getConnection().getLastMsgFromServer();
        if (serverResponse[0].equals("msgRequest") && serverResponse[1].equals("1")) {
            utente.getConnection().sendKeyValue(protocolCodes.MSG.toString(), msg);
            return "Messaggio inviato!";
        }
        return "Il server non ha inviato una risposta valida";
    }
}