package com.example.options;


import com.example.Utente;
import com.example.protocolCodes;

public class SendBroadcastMsg extends Option{
    private String msg;
    public SendBroadcastMsg(Utente utente, String msg) {
        super(utente);
        this.msg = msg;
    }

    @Override
    public String execute() {
        getUtente().getConnection().sendKeyValue(protocolCodes.SWITCH_BROADCAST.toString(), "1");
        String[] serverResponse = getUtente().getConnection().getLastMsgFromServer();
        if (serverResponse[0].equals("msgRequest") && serverResponse[1].equals("1")) {
            getUtente().getConnection().sendKeyValue(protocolCodes.MSG.toString(), msg);
            return "Messaggio inviato!";
        }
        return "Il server non ha inviato una risposta valida";
    }
}
