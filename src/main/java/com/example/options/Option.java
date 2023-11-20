package com.example.options;

import com.example.Utente;
import com.example.protocolCodes;

public abstract class Option {
    protected Utente utente;

    public Option(Utente utente) {
        this.utente = utente;
    }

    public abstract String execute();

    public Utente getUtente(){
        return utente;
    }
    
}
