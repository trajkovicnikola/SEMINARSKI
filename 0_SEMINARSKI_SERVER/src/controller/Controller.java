/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Grad;
import domen.Klijent;
import domen.Pregled;
import domen.StavkaPregleda;
import domen.Tretman;
import domen.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import operacija.grad.UcitajGradoveOperacija;
import operacija.klijent.DodajKlijentaOperacija;
import operacija.klijent.IzmeniKlijentaOperacija;
import operacija.zaposleni.LoginOperacija;
import operacija.klijent.UcitajKlijenteOperacija;
import operacija.pregled.DodajPregledOperacija;
import operacija.pregled.IzmeniPregledOperacija;
import operacija.pregled.ObrisiPregledOperacija;
import operacija.pregled.UcitajPregledOperacija;
import operacija.tretman.DodajTretmanOperacija;
import operacija.tretman.IzmeniTretmanOperacija;
import operacija.tretman.UcitajTretmanOperacija;

/**
 *
 * @author trajk
 */
public class Controller {

    private static Controller instance;
    private List<Zaposleni> ulogovani = new ArrayList<>();

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<Zaposleni> getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(List<Zaposleni> ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        LoginOperacija lo = new LoginOperacija();
        lo.izvrsi(z, null);
        return lo.getZaposleni();
    }

    public List<Klijent> vratiSveKlijente(Klijent klijent) throws Exception {
        UcitajKlijenteOperacija operacija = new UcitajKlijenteOperacija();
        operacija.izvrsi(klijent, null);
        return operacija.getLista();
    }

    public void dodajKlijenta(Klijent k) throws Exception {
        DodajKlijentaOperacija operacija = new DodajKlijentaOperacija();
        operacija.izvrsi(k, null);

    }

    public void izmeniKlijenta(Klijent k) throws Exception {
        IzmeniKlijentaOperacija operacija = new IzmeniKlijentaOperacija();
        operacija.izvrsi(k, null);

    }

    public List<Pregled> ucitajPreglede(Pregled p) throws Exception {
        UcitajPregledOperacija operacija = new UcitajPregledOperacija();
        operacija.izvrsi(p, null);
        return operacija.getLista();
    }

    public void obrisiPregled(Pregled pregled) throws Exception {
        ObrisiPregledOperacija operacija = new ObrisiPregledOperacija();
        operacija.izvrsi(pregled, null);
    }

    public List<Grad> ucitajGradove(Grad g) throws Exception {
        UcitajGradoveOperacija operacija = new UcitajGradoveOperacija();
        operacija.izvrsi(g, null);
        return operacija.getLista();
    }

    public List<Tretman> ucitajTretmane(Tretman t) throws Exception {
        UcitajTretmanOperacija operacija = new UcitajTretmanOperacija();
        operacija.izvrsi(t, null);
        return operacija.getLista();
    }

    public void dodajTretman(Tretman t) throws Exception {
        DodajTretmanOperacija operacija = new DodajTretmanOperacija();
        operacija.izvrsi(t, null);
    }

    public void izmeniTretman(Tretman t) throws Exception {
        IzmeniTretmanOperacija operacija = new IzmeniTretmanOperacija();
        operacija.izvrsi(t, null);
    }

    public void kreirajPregled(Pregled p) throws Exception {
        DodajPregledOperacija operacija = new DodajPregledOperacija();
        operacija.izvrsi(p, null);
    }

    public void izmeniPregled(Pregled p) throws Exception {
        IzmeniPregledOperacija operacija = new IzmeniPregledOperacija();
        operacija.izvrsi(p, null);
    }

    public void logout(Zaposleni z) {
        ulogovani.remove(z);
    }

}
