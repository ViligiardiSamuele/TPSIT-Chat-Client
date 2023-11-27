package me.villo;

import java.util.Scanner;

/**
 * <p>
 * Main
 * </p>
 */
public class App {
    /**
     * <p>
     * main function
     * </p>
     * 
     * @param args args
     */
    public static void main(String[] args) {
        // String ip = "95.235.133.142";
        String ip = "localhost";
        int porta = 2750;
        Scanner scanner = new Scanner(System.in);

        String nome;
        System.out.print("Benvenuto! Inserisci il nome: ");
        do {
            nome = scanner.nextLine();
        } while (nome.trim().equals(""));
        System.out.println("Creazione utente \"" + nome + "\" connesso a \"" + ip + ":" + porta + "\"");
        Utente utente = new Utente(nome, ip, porta);

        System.out.println("Scrivi \"/info\" per visualizzare tutti i comandi");
        String tastiera;
        do {
            do {
                tastiera = scanner.nextLine();
            } while (tastiera.trim().equals(""));
            utente.getActionManager().esegui(tastiera);
        } while (!tastiera.trim().equals("/exit"));
        scanner.close();
        System.exit(0);
    }
}
