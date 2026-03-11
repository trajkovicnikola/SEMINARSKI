/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.tretman;

import com.sun.java.accessibility.util.AWTEventMonitor;
import domen.Tretman;
import forme.tretman.PrikaziTretmanForma;
import forme.model.FormaMod;
import forme.model.ModelTabeleTretman;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author trajk
 */
public class PrikaziTretmanController {
    
    private final PrikaziTretmanForma ptf;

    public PrikaziTretmanController(PrikaziTretmanForma ptf) {
        this.ptf = ptf;
        addActionListeners();
    }

    public void otvoriPrikaziTretmanFormu() {
        pripremiFormu();
        ptf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Tretman> tretmani=Komunikacija.getInstance().ucitajTretmane(new Tretman());
        ModelTabeleTretman mtt=new ModelTabeleTretman(tretmani);
        ptf.getjTableTretman().setModel(mtt);
    }

    private void addActionListeners() {
      
        
        ptf.addIzmeniTretmanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed=ptf.getjTableTretman().getSelectedRow();
                if(selektovaniRed==-1){
                    JOptionPane.showMessageDialog(ptf, "Morate izabrati tretman koji zelite da izmenite","GRESKA",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleTretman mtt=(ModelTabeleTretman) ptf.getjTableTretman().getModel();
                Tretman t=mtt.getLista().get(selektovaniRed);
                Kordinator.getInstance().setParametri("tretman", t);
                Kordinator.getInstance().otvoriDodajTretmanFormu(FormaMod.IZMENI);
            }
        });
    }

    public void osveziTabelu() {
        pripremiFormu();
    }

    
    
    
    
}
