/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
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
import komunikacija.Odgovor;
import static komunikacija.Operacija.LOGIN;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author trajk
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket socket;
    private Primalac primalac;
    private Posiljalac posiljalac;
    private boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket s) {
        socket = s;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);

    }

    @Override
    public void run() {
        try {
            while (!kraj) {
                Zahtev zahtev = (Zahtev) primalac.primi();
                if (zahtev == null) {
                    break;
                }
                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        try {
                            Zaposleni z = (Zaposleni) zahtev.getParametar();
                            z = Controller.getInstance().login(z);
                            odgovor.setOdgovor(z);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_KLIJENTE:
                        Klijent klijent = (Klijent) zahtev.getParametar();
                        List<Klijent> klijenti = Controller.getInstance().vratiSveKlijente(klijent);
                        odgovor.setOdgovor(klijenti);
                        break;
                    case DODAJ_KLIJENTA:

                        try {
                            Klijent k = (Klijent) zahtev.getParametar();
                            Controller.getInstance().dodajKlijenta(k);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case IZMENI_KLIJENTA:
                        try {
                            Controller.getInstance().izmeniKlijenta((Klijent) zahtev.getParametar());
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_PREGLED:
                        Pregled p = (Pregled) zahtev.getParametar();
                        List<Pregled> pregledi = Controller.getInstance().ucitajPreglede(p);
                        odgovor.setOdgovor(pregledi);
                        break;
                    case OBRISI_PREGLED:
                        try {
                            Pregled pregled = (Pregled) zahtev.getParametar();
                            Controller.getInstance().obrisiPregled(pregled);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_GRADOVE:
                        List<Grad> gradovi = Controller.getInstance().ucitajGradove((Grad) zahtev.getParametar());
                        odgovor.setOdgovor(gradovi);
                        break;
                    case UCITAJ_TRETMANE:
                        List<Tretman> tretmani = Controller.getInstance().ucitajTretmane((Tretman) zahtev.getParametar());
                        odgovor.setOdgovor(tretmani);
                        break;
                    case DODAJ_TRETMAN:
                        try {
                            Tretman t = (Tretman) zahtev.getParametar();
                            Controller.getInstance().dodajTretman(t);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case IZMENI_TRETMAN:
                        try {
                            Tretman t = (Tretman) zahtev.getParametar();
                            Controller.getInstance().izmeniTretman(t);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case KREIRAJ_PREGLED:
                        try {
                            p = (Pregled) zahtev.getParametar();
                            Controller.getInstance().kreirajPregled(p);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case LOGOUT:
                        try {
                            Zaposleni z = (Zaposleni) zahtev.getParametar();
                            Controller.getInstance().logout(z);
                            odgovor.setOdgovor(null);
                            kraj=true;
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    default:
                        System.out.println("GRESKA POGRESNA OPERACIJA");
                }
                posiljalac.posalji(odgovor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void prekini() {
        try {
            kraj = true;
            socket.close();
            interrupt();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
