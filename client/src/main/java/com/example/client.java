package com.example;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 3000);
        String a;
        boolean b = true;
        // per parlare
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);

        // per ascoltare
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // per la tastiera
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

        pr.println("Eccomi");
        System.out.println(br.readLine()); 
        pr.println(tastiera.readLine()); 
        System.out.println(br.readLine()); 
        
        do {
            pr.println(tastiera.readLine());
            a = br.readLine();
            if(a.equals("fine")){
                b = false;
            }else{
                System.out.println(a);
            }

        } while (b);
        s.close();
    }
}