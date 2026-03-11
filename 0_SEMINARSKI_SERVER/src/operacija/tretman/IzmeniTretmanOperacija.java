/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.tretman;

import domen.Tretman;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author trajk
 */
public class IzmeniTretmanOperacija extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
         if (param == null || !(param instanceof Tretman)) {
            throw new Exception("Sistem ne moze da kreira tretman");

        }

        Tretman tretman = (Tretman) param;

        if (tretman.getCena() <500 || tretman.getCena()>10000) {
            throw new Exception("Cena mora biti izmedju 500 i 10000");
        }

        if (tretman.getNaziv() == null || tretman.getNaziv().trim().length() < 4) {
            throw new Exception("Naziv mora da sadrzi bar 5 karaktera");
        }

        if (tretman.getOpis() == null || tretman.getOpis().trim().length() < 20) {
            throw new Exception("Opis tretmana mora da sadrzi bar 20 karaktera");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Tretman)param);
    }
    
}
