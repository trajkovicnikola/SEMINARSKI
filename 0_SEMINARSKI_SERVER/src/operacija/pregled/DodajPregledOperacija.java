/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.pregled;

import domen.Pregled;
import domen.StavkaPregleda;
import java.util.Date;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author trajk
 */
public class DodajPregledOperacija extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Pregled)){
            throw new Exception("Sistem ne moze da kreira pregled");
        }
        Pregled p=(Pregled)param;
        if(p.getStavkePregleda()==null || p.getStavkePregleda().isEmpty()){
            throw new Exception("Pregled mora imati bar jednu stavku pregleda");
        }
        Date sada = new Date();
        if(p.getDatumPregleda()==null){
            throw new Exception("Datum pregleda mora biti unesen");
        }
        if(!p.getDatumPregleda().after(sada)){
            throw new Exception("Datum pregleda mora biti u buducnosti");
        }
        if(!p.getDatumPregleda().after(p.getDatumRezervacije())){
            throw new Exception("Datum pregleda mora biti posle datuma rezervacije");
        }
        List<Pregled> sviPregledi = broker.getAll(new Pregled(), null);
        for (Pregled postojeci : sviPregledi) {
            if (postojeci.getDatumPregleda().equals(p.getDatumPregleda())) {
                if (postojeci.getZaposleni().getZaposleniID() == p.getZaposleni().getZaposleniID()) {
                    throw new Exception("Zaposleni vec ima pregled u to vreme");
                }
                if (postojeci.getKlijent().getKlijentID() == p.getKlijent().getKlijentID()) {
                    throw new Exception("Klijent vec ima pregled u to vreme");
                }
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Pregled p=(Pregled)param;
        int pregledID=broker.addReturnKey(p);
        if(pregledID==-1){
             throw new Exception("Sistem ne moze da kreira pregled");
        }
        List<StavkaPregleda> stavke=p.getStavkePregleda();
        for(StavkaPregleda stavka:stavke){
            stavka.setPregledID(pregledID);
            broker.add(stavka);
        }
    }
    
    
    
}
