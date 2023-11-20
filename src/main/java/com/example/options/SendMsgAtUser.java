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
        utente.getConnection().sendKeyValue(protocolCodes.SWITCH_TO_USER.toString(), destinatario);
        utente.getConnection().sendKeyValue(protocolCodes.MSG.toString(), msg);
        return "Messaggio inviato!";
    }

}
