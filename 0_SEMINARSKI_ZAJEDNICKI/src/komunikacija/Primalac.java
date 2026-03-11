/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trajk
 */
public class Primalac {
    
    private Socket socket;
    private ObjectInputStream in;

    public Primalac(Socket socket) {
        this.socket = socket;
        try {
            in=new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Object primi(){
        try {
            
            return in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
