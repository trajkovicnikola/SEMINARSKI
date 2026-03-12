/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Grad;
import domen.Klijent;
import domen.Pregled;
import domen.StavkaPregleda;
import domen.Tretman;
import domen.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trajk
 */
public class Komunikacija {

    private Socket socket;
    private static Komunikacija instance;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private Komunikacija() {

        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            throw new RuntimeException("Server nije pokrenut.");
        }

    }

    public static Komunikacija getInstance() {
        if (instance == null) {

            instance = new Komunikacija();

        }
        return instance;
    }

    private Odgovor primiOdgovor() {
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor == null) {
            throw new RuntimeException("Server je ugasen");
        }
        return odgovor;
    }

    public Zaposleni login(String email, String sifra) throws Exception {

        Zaposleni z = new Zaposleni();
        z.setEmail(email);
        z.setSifra(sifra);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, z);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }
        z = (Zaposleni) odgovor.getOdgovor();
        return z;
    }

    public List<Klijent> ucitajKlijente(Klijent k) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KLIJENTE, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        List<Klijent> sviKlijenti = (List<Klijent>) odgovor.getOdgovor();
        return sviKlijenti;
    }

    public void dodajKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        if (odgovor.getOdgovor() != null) {
            throw (Exception) odgovor.getOdgovor();
        }
    }

    public void izmeniKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        if (odgovor.getOdgovor() != null) {
            throw (Exception) odgovor.getOdgovor();
        }
    }

    public List<Pregled> ucitajPreglede(Pregled p) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PREGLED, p);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        List<Pregled> lista = (List<Pregled>) odgovor.getOdgovor();
        return lista;
    }

    public void obrisiPregled(Pregled p) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_PREGLED, p);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        if (odgovor.getOdgovor() != null) {
            throw (Exception) odgovor.getOdgovor();
        }
    }

    public List<Grad> ucitajGradove(Grad g) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_GRADOVE, g);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        List<Grad> gradovi = (List<Grad>) odgovor.getOdgovor();
        return gradovi;
    }

    public List<Tretman> ucitajTretmane(Tretman t) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TRETMANE, t);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        List<Tretman> tretmani = (List<Tretman>) odgovor.getOdgovor();
        return tretmani;
    }

    public void dodajTretman(Tretman t) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_TRETMAN, t);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        if (odgovor.getOdgovor() != null) {
            throw (Exception) odgovor.getOdgovor();
        }
    }

    public void izmeniTretman(Tretman t) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_TRETMAN, t);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        if (odgovor.getOdgovor() != null) {
            throw (Exception) odgovor.getOdgovor();
        }
    }

    public void dodajPregled(Pregled p) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_PREGLED, p);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = primiOdgovor();
        if (odgovor.getOdgovor() != null) {
            throw (Exception) odgovor.getOdgovor();
        }
    }

    public void logout(Zaposleni z) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.LOGOUT, z);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = primiOdgovor();
        if (odgovor.getOdgovor() != null) {
            throw (Exception) odgovor.getOdgovor();
        }

        if (socket != null && !socket.isClosed()) {
            socket.close();
        }

        instance = null;
    }

}
