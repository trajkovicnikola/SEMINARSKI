/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.pregled;

import domen.Pregled;
import domen.StavkaPregleda;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author trajk
 */
public class ObrisiPregledOperacija extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Pregled)){
            throw new Exception("Sistem ne moze da obrise racun");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Pregled p=(Pregled)param;
        List<StavkaPregleda> stavke=p.getStavkePregleda();
        for(StavkaPregleda stavka:stavke){
            broker.delete(stavka);
        }
        broker.delete(p);
    }
    
}
