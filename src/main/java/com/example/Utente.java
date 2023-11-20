package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.options.SendBroadcastMsg;
import com.example.options.SendCustomTxt;
import com.example.options.SendMsgAtUser;
import com.example.options.SendMsgAtUsers;
import com.example.options.ShowUsers;

public class Utente {
    private String nome;
    private Connection connection;

    public Utente(String nome, Connection connection) {
        this.nome = nome;
        this.connection = connection;
    }

    public void options(int option) {
        switch (option) {
            case 1:
                System.out.println(new ShowUsers(this).execute());
                break;
            case 2: //OK
                String destinatario, msg;
                Scanner s = new Scanner(System.in);
                //chiede destinatario
                System.out.print("Inserisci destinatario: ");
                do{
                    destinatario = s.nextLine();
                } while(destinatario.trim() == "");
                //chiede messaggio
                System.out.print("Scrivi il messaggio per " + destinatario + " (\"Invio\" per inviare): ");
                do{
                    msg = s.nextLine();
                } while(msg.trim() == "");
                System.out.println("Invio messaggio...");
                //esegue
                System.out.println(new SendMsgAtUser(this, destinatario, msg).execute());
                break;
            case 3:
                List<String> users = new ArrayList<>();
                Scanner scanner = new Scanner(System.in);
                Boolean continua = true;
                do {
                    System.out.print("Nome dell' Utente: ");
                    users.add(scanner.nextLine());

                } while (continua);
                new SendMsgAtUsers(this, null).execute();
                break;
            case 4:
                new SendBroadcastMsg(this).execute();
                break;
            case 5:
                new SendCustomTxt(this).execute();
                break;
            case 0:
                connection.close();
                break;
            default:
                System.out.println("Scelta invalida...");
        }
    }

    public String getNome() {
        return this.nome;
    }

    public Connection getConnection() {
        return this.connection;
    }

}
