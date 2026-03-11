/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.klijent;

import domen.Grad;
import domen.Klijent;
import domen.Zaposleni;
import forme.klijent.PretraziKlijentaForma;
import forme.model.FormaMod;
import forme.model.ModelTabeleKlijent;
import forme.model.ModelTabeleZaposleni;
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
public class PretraziKlijentaController {

    private final PretraziKlijentaForma pkf;

    public PretraziKlijentaController(PretraziKlijentaForma pkf) {
        this.pkf = pkf;
        addActionListeners();
    }

    public void otvoriPretraziKlijentFormu() {
        pripremiFormu();
        pkf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Klijent> klijenti = komunikacija.Komunikacija.getInstance().ucitajKlijente(new Klijent());
        ModelTabeleKlijent mtk = new ModelTabeleKlijent(klijenti);
        pkf.getjTableKlijent().setModel(mtk);
         List<Grad> gradovi=Komunikacija.getInstance().ucitajGradove(new Grad());
        for(Grad grad:gradovi)
            pkf.getjComboBoxGrad().addItem(grad);
    }

    private void addActionListeners() {
        pkf.addIzmeniKlijentaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = pkf.getjTableKlijent().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pkf, "Morate izabrati klijenta", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijent().getModel();
                Klijent k = mtk.getListaKlijenata().get(selektovaniRed);
                Kordinator.getInstance().setParametri("klijent", k);
                Kordinator.getInstance().otvoriDodajKlijentaFormu(FormaMod.IZMENI);
            }
        });

        pkf.addPretraziKlijentaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pkf.getjTextFieldIme().getText().trim();
                String prezime = pkf.getjTextFieldPrezime().getText().trim();
                Grad grad=new Grad();
                if(pkf.getjCheckBoxGrad().isSelected()){
                    grad=(Grad) pkf.getjComboBoxGrad().getSelectedItem();
                }
                
                Klijent k=new Klijent(-1,ime,prezime, "", grad);
                List<Klijent> klijenti=Komunikacija.getInstance().ucitajKlijente(k);
                ModelTabeleKlijent mtk=new ModelTabeleKlijent(klijenti);
                pkf.getjTableKlijent().setModel(mtk);
            }
        });
        
        pkf.addResetujPretraguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pkf.getjComboBoxGrad().setSelectedIndex(0);
                pkf.getjTextFieldIme().setText("");
                pkf.getjTextFieldPrezime().setText("");
                osveziTabelu();
            }
        });
    }

    public void osveziTabelu() {
        pripremiFormu();
    }

}
