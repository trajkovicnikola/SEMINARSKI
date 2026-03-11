/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.util.List;
import niti.ObradaKlijentskihZahteva;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;

/**
 *
 * @author trajk
 */
public class Server extends Thread{

    private boolean kraj = false;
    private ServerSocket serverSocket;
    private List<ObradaKlijentskihZahteva> klijenti=new ArrayList<>();

    @Override
    public void run() {
        try {
            int port=Integer.parseInt(Konfiguracija.getInstance().getProperty("port"));
            serverSocket = new ServerSocket(port);
            while (!kraj) {
                Socket s = serverSocket.accept();
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                klijenti.add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    


    public void zaustaviServer() {
        try {
            kraj = true;
            serverSocket.close();
            for(ObradaKlijentskihZahteva okz:klijenti)
                okz.prekini();
            interrupt();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
