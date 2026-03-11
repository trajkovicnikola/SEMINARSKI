/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.pregled;

import domen.Pregled;
import domen.StavkaPregleda;
import forme.pregled.PrikaziPregledForma;
import forme.model.ModelTabelePregled;
import forme.model.ModelTabeleStavka;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import komunikacija.Komunikacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author trajk
 */
public class PrikaziPregledController {

    private final PrikaziPregledForma pkf;

    public PrikaziPregledController(PrikaziPregledForma pkf) {
        this.pkf = pkf;
        addMouseListener();
        addActionListeners();
    }

    public void otvoriPrikaziPregledFormu() {
        pripremiPodatke();
        pkf.setVisible(true);
    }

    private void pripremiPodatke() {
        List<Pregled> lista = Komunikacija.getInstance().ucitajPreglede(new Pregled());
        ModelTabelePregled mtp = new ModelTabelePregled(lista);
        pkf.getjTablePregled().setModel(mtp);

        List<StavkaPregleda> stavke = new ArrayList<>();
        ModelTabeleStavka mts = new ModelTabeleStavka(stavke);
        pkf.getjTableStavkaPregleda().setModel(mts);
    }

    private void addMouseListener() {
        pkf.getjTablePregled().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selektovaniRed = pkf.getjTablePregled().getSelectedRow();
                if (selektovaniRed != -1) {
                    ModelTabelePregled mtp = (ModelTabelePregled) pkf.getjTablePregled().getModel();
                    Pregled p = mtp.getLista().get(selektovaniRed);
                    ModelTabeleStavka mts = new ModelTabeleStavka(p.getStavkePregleda());
                    pkf.getjTableStavkaPregleda().setModel(mts);
                }
            }

        });
    }

    private void addActionListeners() {
        pkf.addPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiPodatke();
                Date izabraniDatum = pkf.getjDateChooser().getDate();

//                if (izabraniDatum == null) {
//                    JOptionPane.showMessageDialog(pkf, "Izaberite datum.", "GRESKA", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
                Pregled p=new Pregled();
                p.setDatumPregleda(izabraniDatum);
                List<Pregled> pregledi=Komunikacija.getInstance().ucitajPreglede(p);
                ModelTabelePregled mtp = new ModelTabelePregled(pregledi);
                pkf.getjTablePregled().setModel(mtp);
                

                List<StavkaPregleda> stavke = new ArrayList<>();
                ModelTabeleStavka mts = new ModelTabeleStavka(stavke);
                pkf.getjTableStavkaPregleda().setModel(mts);
            }

        });

        pkf.addResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiPodatke();
            }
        });

        pkf.addObrisiPregledActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = pkf.getjTablePregled().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pkf, "Morate izabrati pregled koji zalite da obrisete", "GRESKA",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabelePregled mtp = (ModelTabelePregled) pkf.getjTablePregled().getModel();
                Pregled p = mtp.getLista().get(selektovaniRed);
                try {
                    Komunikacija.getInstance().obrisiPregled(p);
                    JOptionPane.showMessageDialog(pkf, "Sistem je uspesno obrisao pregled", "GRESKA",JOptionPane.INFORMATION_MESSAGE);
                    pripremiPodatke();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da obrise pregled", "GRESKA",JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
    }

}
