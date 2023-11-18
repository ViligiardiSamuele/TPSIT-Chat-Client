package com.example.options;

import java.util.Scanner;

import com.example.Utente;

public class SendCustomTxt extends Option {

    public SendCustomTxt(Utente utente) {
        super(utente);
    }

    @Override
    public String execute() {
        Scanner s = new Scanner(System.in);
        System.out.print("Messaggio: ");
        try {
            getUtente().getConnection().getOut().writeBytes(s.nextLine());
        } catch (Exception e) {
            System.out.println("gay");
        }
        s.close();
        return "";
    }

}
