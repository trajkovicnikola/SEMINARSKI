/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.klijent;

import domen.Grad;
import domen.Klijent;
import forme.klijent.DodajKlijentaForma;
import forme.model.FormaMod;
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
public class DodajKlijentaController {

    private final DodajKlijentaForma dkf;

    public DodajKlijentaController(DodajKlijentaForma dkf, FormaMod mod) {
        this.dkf = dkf;
        pripremiFormu();
        if (mod.equals(FormaMod.DODAJ)) {
            dkf.getjButtonDodaj().setVisible(true);
            dkf.getjButtonIzmeni().setVisible(false);
        }
        if (mod.equals(FormaMod.IZMENI)) {
            dkf.getjButtonDodaj().setVisible(false);
            dkf.getjButtonIzmeni().setVisible(true);
            Klijent k = (Klijent) Kordinator.getInstance().getParametri("klijent");
            dkf.getjTextFieldIme().setText(k.getIme());
            dkf.getjTextFieldPrezime().setText(k.getPrezime());
            dkf.getjTextFieldBroj().setText(k.getTelefon());
            dkf.getjComboBoxGrad().setSelectedItem(k.getGrad());
        }
        addActionListeners();
    }

    public void otvoriDodajKlijentaFormu() {

        

        dkf.setVisible(true);
    }

    private void addActionListeners() {
        dkf.dodajKlijentaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dkf.getjTextFieldIme().getText().isEmpty() || dkf.getjTextFieldPrezime().getText().isEmpty() || dkf.getjTextFieldPrezime().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Sve forme moraju biti popunjene", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String ime = dkf.getjTextFieldIme().getText().trim();
                String prezime = dkf.getjTextFieldPrezime().getText().trim();
                String broj = dkf.getjTextFieldBroj().getText().trim();
                Grad grad = (Grad) dkf.getjComboBoxGrad().getSelectedItem();
                Klijent k = new Klijent(-1, ime, prezime, broj, grad);
                try {
                    Komunikacija.getInstance().dodajKlijenta(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je uspesno dodao klijenta", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                    kordinator.Kordinator.getInstance().osveziComboKlijent();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        dkf.izmeniKlijentaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dkf.getjTextFieldIme().getText().isEmpty() || dkf.getjTextFieldPrezime().getText().isEmpty() || dkf.getjTextFieldPrezime().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dkf, "Sve forme moraju biti popunjene", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Klijent k = (Klijent) Kordinator.getInstance().getParametri("klijent");
                String ime = dkf.getjTextFieldIme().getText().trim();
                String prezime = dkf.getjTextFieldPrezime().getText().trim();
                String broj = dkf.getjTextFieldBroj().getText().trim();
                Grad g=(Grad) dkf.getjComboBoxGrad().getSelectedItem();
                k.setGrad(g);
                k.setIme(ime);
                k.setPrezime(prezime);
                k.setTelefon(broj);
                try {
                    Komunikacija.getInstance().izmeniKlijenta(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je uspesno izmenio podatke o klijentu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                    kordinator.Kordinator.getInstance().osveziComboKlijent();
                    Kordinator.getInstance().osveziTabeluKlijent();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, ex.getMessage(), "GRESKA!", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    private void pripremiFormu() {
        List<Grad> gradovi = Komunikacija.getInstance().ucitajGradove(new Grad());
        for (Grad grad : gradovi) {
            dkf.getjComboBoxGrad().addItem(grad);
        }
    }

}
