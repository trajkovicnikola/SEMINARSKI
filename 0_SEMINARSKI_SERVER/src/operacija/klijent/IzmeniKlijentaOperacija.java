/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.klijent;

import domen.Klijent;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author trajk
 */
public class IzmeniKlijentaOperacija extends ApstraktnaGenerickaOperacija {
    


    @Override
    protected void preduslovi(Object param) throws Exception {
              if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Sistem ne moze da izmeni klijenta");
        }

        Klijent k = (Klijent) param;

        if (k.getIme() == null || k.getIme().trim().length() < 3) {
            throw new Exception("Ime mora imati najmanje 3 slova.");
        }

        if (k.getPrezime() == null || k.getPrezime().trim().length() < 3) {
            throw new Exception("Prezime mora imati najmanje 3 slova.");
        }

        if (k.getTelefon() == null || !k.getTelefon().matches("^\\d{3}/\\d{6,7}$")) {
            throw new Exception("Telefon mora biti u formatu xxx/xxxxxx ili xxx/xxxxxxx.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Klijent)param);
       
    }

    
    
}
