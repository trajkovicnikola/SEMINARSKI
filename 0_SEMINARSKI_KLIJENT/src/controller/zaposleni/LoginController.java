/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.zaposleni;

import domen.Zaposleni;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author trajk
 */
public class LoginController {

    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(e);
            }

            private void login(ActionEvent e) {
                try {
                    String email = lf.getjTextFieldEmail().getText().trim();
                    String sifra = String.valueOf(lf.getjPasswordField1().getPassword());
                    Zaposleni ulogovani = Komunikacija.getInstance().login(email, sifra);
                    kordinator.Kordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra su ispravni", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    lf.dispose();
                    kordinator.Kordinator.getInstance().otvoriGlavnuFormu();
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(lf, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);

                }

            }

        });

    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
