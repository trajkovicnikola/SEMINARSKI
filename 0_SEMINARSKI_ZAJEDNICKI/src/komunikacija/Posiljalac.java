/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trajk
 */
public class Posiljalac {
    
    private Socket socket;
    private ObjectOutputStream out;

    public Posiljalac(Socket socket) {
        this.socket = socket;
        try {
            out=new ObjectOutputStream(socket.getOutputStream());
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Posiljalac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void posalji(Object obj){
        try {
            
            out.writeObject(obj);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Posiljalac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
