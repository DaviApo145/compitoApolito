package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class ClientHandler extends Thread {
    private Socket s;
    private PrintWriter pr = null;
    private BufferedReader br = null;
    private int contatore;
    private static String nameServer = "server 1";
    private static int biglietti = 30;

    public ClientHandler(Socket s, int c){
        setName(nameServer);
        this.s = s;
        contatore = c;
        
        try {
            // per parlare
            pr = new PrintWriter(s.getOutputStream(), true);
            // per ascoltare
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            System.out.println(br.readLine());
            pr.println("Ciao come ti chiami?"); // invio messaggio
            String nome = br.readLine(); // ricevo: il nome
            String nomeUpper = nome.toUpperCase(); // nome in maiuscolo
            System.out.println("Utente di nome " + nomeUpper + " connesso"); // console: nome ricevuto
            pr.println("Salve " + nomeUpper + " sei l'utente connesso numero " + contatore + ", premi D per richiedere la qauntità, premi A per acquistare o  premi Q per la disconnessione"); // invio messaggio

            boolean b = true;
            while(b){
                String scegli = br.readLine();
                switch(scegli){
                  case("D"):
                  pr.println("Disponibili " + biglietti + " biglietti");
                  break;
                  case("A"):
                   if(biglietti > 0){
                   pr.println("Biglietto acquistato");
                   biglietti = biglietti -1;
                   }
                   if(biglietti == 0){
                    pr.println("Non ci sono più biglietti");
                   }
                   break;
                  case("Q"):
                    pr.println("fine");
                 break;
               
            }
        }
        s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
            
       
    }

}