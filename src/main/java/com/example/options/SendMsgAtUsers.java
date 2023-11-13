package com.example.options;

import java.util.ArrayList;
import java.util.List;

import com.example.Utente;

public class SendMsgAtUsers extends Option{
    private List<String> users;
    public SendMsgAtUsers(Utente utente, ArrayList<String> users) {
        super(utente);
        this.users = users;
    }

    @Override
    public String execute() {
        
        return "";
    }

}
