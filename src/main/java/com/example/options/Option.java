package com.example.options;


import com.example.Utente;

public abstract class Option {
    private Utente utente;

    public Option(Utente utente) {
        this.utente = utente;
    }

    public abstract String execute();

    public Utente getUtente(){
        return utente;
    }
    
}
