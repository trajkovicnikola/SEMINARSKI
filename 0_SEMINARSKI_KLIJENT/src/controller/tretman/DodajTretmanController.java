/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.tretman;

import domen.Tretman;
import forme.tretman.DodajTretmanForma;
import forme.model.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author trajk
 */
public class DodajTretmanController {

    private final DodajTretmanForma dtf;

    public DodajTretmanController(DodajTretmanForma dtf, FormaMod mod) {
        this.dtf = dtf;
        if (mod.equals(FormaMod.DODAJ)) {

            dtf.getjTextAreaOpis().setText("");
            dtf.getjTextFieldCena().setText("");
            dtf.getjTextFieldNaziv().setText("");
            dtf.getjButtonDodaj().setVisible(true);
            dtf.getjButtonIzmeni().setVisible(false);
        }
        if(mod.equals(FormaMod.IZMENI)){
            Tretman t=(Tretman) Kordinator.getInstance().getParametri("tretman");
            dtf.getjTextAreaOpis().setText(t.getOpis());
            dtf.getjTextFieldCena().setText(t.getCena()+"");
            dtf.getjTextFieldNaziv().setText(t.getNaziv());
            dtf.getjButtonDodaj().setVisible(false);
            dtf.getjButtonIzmeni().setVisible(true);
        }
        addActionListeners();
    }

    public void otvoriDodajTretmanFormu() {
        dtf.setVisible(true);
    }

    private void addActionListeners() {
        dtf.dodajTretmanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dtf.getjTextAreaOpis().getText().isEmpty() || dtf.getjTextFieldCena().getText().isEmpty() || dtf.getjTextFieldNaziv().getText().isEmpty()){
                    JOptionPane.showMessageDialog(dtf, "Morate popuniti sva polja", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String naziv = dtf.getjTextFieldNaziv().getText().trim();
                String opis = dtf.getjTextAreaOpis().getText().trim();
                double cena = 0;
                try {
                    cena = Double.parseDouble(dtf.getjTextFieldCena().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dtf, "Vrednost atributa cena mora biti broj", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Tretman t = new Tretman(-1, naziv, opis, cena);
                try {
                    Komunikacija.getInstance().dodajTretman(t);
                    JOptionPane.showMessageDialog(dtf, "Sistem je uspesno kreirao tretman", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().osveziComboTretman();
                    dtf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dtf, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        dtf.izmeniTretmanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(dtf.getjTextAreaOpis().getText().isEmpty() || dtf.getjTextFieldCena().getText().isEmpty() || dtf.getjTextFieldNaziv().getText().isEmpty()){
                    JOptionPane.showMessageDialog(dtf, "Morate popuniti sva polja", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String naziv = dtf.getjTextFieldNaziv().getText().trim();
                String opis = dtf.getjTextAreaOpis().getText().trim();
                double cena = 0;
                try {
                    cena = Double.parseDouble(dtf.getjTextFieldCena().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dtf, "Vrednost atributa cena mora biti broj", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Tretman t=(Tretman) Kordinator.getInstance().getParametri("tretman");
                t.setNaziv(naziv);
                t.setCena(cena);
                t.setOpis(opis);
                try {
                    Komunikacija.getInstance().izmeniTretman(t);
                    JOptionPane.showMessageDialog(dtf, "Sistem je uspesno izmenio tretman", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().osveziComboTretman();
                    Kordinator.getInstance().osveziTabeluTretman();
                    dtf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dtf, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
