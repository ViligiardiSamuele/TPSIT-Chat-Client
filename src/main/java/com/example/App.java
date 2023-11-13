package com.example;

import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try {
            Connection connection = new Connection(new Socket("172.20.10.10",2750));
            connection.getOut().writeBytes("ciao\n");
            Scanner scanner = new Scanner(System.in);
            String nome = "";
            do {
                System.out.print("Inserisci un nome: ");
                nome = scanner.nextLine();
            } while (nome.trim() == "");
            System.out.println("Benvenuto " + nome + "!");
            Utente  utente = new Utente(nome, connection);
            int scelta = 0;
            do {
                System.out.println("--- Scegli un'azione ------------");
                System.out.println("1 - Visualizza utenti connessi");
                System.out.println("2 - Invia messaggio in privato");
                System.out.println("3 - Invia messaggio in gruppo");
                System.out.println("4 - Invia messaggio in broadcast");
                System.out.println("0 - Disconnettiti");
                System.out.print("Scelta: ");
                try {
                    scelta = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Input non valido...");
                    scelta = 100;
                }
                scanner.nextLine();
                utente.options(scelta);
            } while (scelta != 0);
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}
