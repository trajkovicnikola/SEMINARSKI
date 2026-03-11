/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kordinator;

import controller.klijent.DodajKlijentaController;

import controller.GlavnaFormaController;

import controller.zaposleni.LoginController;
import controller.klijent.PretraziKlijentaController;
import controller.pregled.PrikaziPregledController;
import controller.tretman.DodajTretmanController;
import controller.tretman.PrikaziTretmanController;
import domen.Zaposleni;
import forme.klijent.DodajKlijentaForma;
import forme.tretman.DodajTretmanForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.klijent.PretraziKlijentaForma;
import forme.pregled.PrikaziPregledForma;
import forme.tretman.PrikaziTretmanForma;
import forme.model.FormaMod;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author trajk
 */
public class Kordinator {

    private static Kordinator instance;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PretraziKlijentaController pretraziKlijentaController;
    private DodajKlijentaController dodajKlijentaController;
    private Zaposleni ulogovani;
    private PrikaziPregledController prikaziPregledController;
    private Map<String, Object> parametri;
    private PrikaziTretmanController prikaziTretmanController;
    private DodajTretmanController dodajTretmanController;

    private Kordinator() {
        parametri = new HashMap<>();
    }

    public static Kordinator getInstance() {
        if (instance == null) {
            instance = new Kordinator();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriGlavnuFormu();
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriPretraziKlijentFormu() {
        pretraziKlijentaController = new PretraziKlijentaController(new PretraziKlijentaForma());
        pretraziKlijentaController.otvoriPretraziKlijentFormu();
    }

    public void otvoriDodajKlijentaFormu(FormaMod mod) {
        dodajKlijentaController = new DodajKlijentaController(new DodajKlijentaForma(), mod);
        dodajKlijentaController.otvoriDodajKlijentaFormu();
    }

    public Object getParametri(String s) {
        return parametri.get(s);
    }

    public void setParametri(String s, Object o) {
        parametri.put(s, o);
    }

   

    public void osveziTabeluKlijent() {
        pretraziKlijentaController.osveziTabelu();
    }

    public void otvoriPrikaziPregledFormu() {
        prikaziPregledController = new PrikaziPregledController(new PrikaziPregledForma());
        prikaziPregledController.otvoriPrikaziPregledFormu();
    }

    public void otvoriPrikaziTretmanFormu() {
        prikaziTretmanController = new PrikaziTretmanController(new PrikaziTretmanForma());
        prikaziTretmanController.otvoriPrikaziTretmanFormu();
    }

    public void otvoriDodajTretmanFormu(FormaMod formaMod) {
        dodajTretmanController = new DodajTretmanController(new DodajTretmanForma(), formaMod);
        dodajTretmanController.otvoriDodajTretmanFormu();
    }

    public void osveziTabeluTretman() {
        prikaziTretmanController.osveziTabelu();
    }


    public void osveziComboTretman() {
        glavnaFormaController.osveziComboTretman();
    }

    public void osveziComboKlijent() {
        glavnaFormaController.osveziComboKlijent();
    }

}
