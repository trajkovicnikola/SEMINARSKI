/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Klijent;
import domen.StavkaPregleda;
import domen.Tretman;
import domen.Zaposleni;
import forme.GlavnaForma;
import forme.model.ModelTabeleStavka;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings;
import domen.Pregled;
import forme.model.FormaMod;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author trajk
 */
public class GlavnaFormaController {

    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        gf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addActionListeners();
        addWindowActionListeners();
    }

    public void otvoriGlavnuFormu() {
        Zaposleni ulogovani = kordinator.Kordinator.getInstance().getUlogovani();
        pripremiFormu();
        gf.setVisible(true);
        gf.getjLabelUlogovani().setText(ulogovani.toString());
    }

    private void addActionListeners() {
        gf.pretraziKlijentaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kordinator.Kordinator.getInstance().otvoriPretraziKlijentFormu();
            }
        });

        gf.dodajKlijentaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kordinator.Kordinator.getInstance().otvoriDodajKlijentaFormu(FormaMod.DODAJ);
            }
        });

        gf.prikaziPregledActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kordinator.getInstance().otvoriPrikaziPregledFormu();
            }
        });

        gf.prikaziTretmanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kordinator.getInstance().otvoriPrikaziTretmanFormu();
            }
        });

        gf.dodajTretmanActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kordinator.getInstance().otvoriDodajTretmanFormu(FormaMod.DODAJ);
            }
        });

        gf.dodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tretman t = (Tretman) gf.getjComboBoxTretman().getSelectedItem();
                int kolicina = 0;
                try {
                    kolicina = Integer.parseInt(gf.getjTextFieldKolicina().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(gf, "Kolicina mora biti broj", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (kolicina <= 0) {
                    JOptionPane.showMessageDialog(gf, "Kolicina mora biti broj veci od 0", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                StavkaPregleda sp = new StavkaPregleda();
                sp.setTretman(t);
                sp.setKolicina(kolicina);
                sp.setCena(t.getCena());
                sp.setIznos(kolicina * t.getCena());
                ModelTabeleStavka mts = (ModelTabeleStavka) gf.getjTableStavka().getModel();
                mts.dodajStavku(sp);
            }
        });

        gf.obrisiStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = gf.getjTableStavka().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(gf, "Morate izabrati stavku koju zelite da obrisete", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleStavka mts = (ModelTabeleStavka) gf.getjTableStavka().getModel();
                mts.obrisiStavku(selektovaniRed);
            }
        });

        gf.kreirajPregledActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime ldt = gf.getDateTimePicker().getDateTimeStrict();
                if (ldt == null) {
                    JOptionPane.showMessageDialog(gf, "Morate izabrati datum i vreme pregleda", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Date datumPregleda = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                Klijent k = (Klijent) gf.getjComboBoxKlijent().getSelectedItem();
                ModelTabeleStavka mts = (ModelTabeleStavka) gf.getjTableStavka().getModel();
                List<StavkaPregleda> stavke = mts.getLista();
                double ukupanIznos = 0;
                for (StavkaPregleda stavka : stavke) {
                    ukupanIznos += stavka.getIznos();
                }
                Zaposleni z = Kordinator.getInstance().getUlogovani();
                Pregled p = new Pregled(-1, z, k, new Date(), datumPregleda, ukupanIznos, stavke);
                try {
                    Komunikacija.getInstance().dodajPregled(p);
                    JOptionPane.showMessageDialog(gf, "Sistem je uspesno kreirao pregled", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(gf, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void pripremiFormu() {
        gf.getDateTimePicker().getDatePicker().getSettings().setAllowKeyboardEditing(false);
        TimePickerSettings tps = gf.getDateTimePicker().getTimePicker().getSettings();
        tps.setAllowKeyboardEditing(false);
        tps.use24HourClockFormat();
        tps.generatePotentialMenuTimes(
                TimePickerSettings.TimeIncrement.ThirtyMinutes,
                LocalTime.of(8, 0),
                LocalTime.of(18, 0)
        );
        ModelTabeleStavka mts = new ModelTabeleStavka(new ArrayList<>());
        gf.getjTableStavka().setModel(mts);
        osveziComboKlijent();
        osveziComboTretman();
    }

    public void osveziComboKlijent() {
        gf.getjComboBoxKlijent().removeAllItems();
        List<Klijent> klijenti = Komunikacija.getInstance().ucitajKlijente(new Klijent());
        for (Klijent k : klijenti) {
            gf.getjComboBoxKlijent().addItem(k);
        }
    }

    public void osveziComboTretman() {
        gf.getjComboBoxTretman().removeAllItems();
        List<Tretman> tretmani = Komunikacija.getInstance().ucitajTretmane(new Tretman());
        for (Tretman t : tretmani) {
            gf.getjComboBoxTretman().addItem(t);
        }
    }

    private void addWindowActionListeners() {
        gf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logoutAndExit();
            }
        });
    }

    private void logoutAndExit() {
        try {
            Zaposleni z = Kordinator.getInstance().getUlogovani();
            if (z != null) {
                Komunikacija.getInstance().logout(z);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Kordinator.getInstance().setUlogovani(null);
            gf.dispose();
            System.exit(0);
        }
    }

}
