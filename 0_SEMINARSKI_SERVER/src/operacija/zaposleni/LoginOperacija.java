/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zaposleni;

import controller.Controller;
import domen.Zaposleni;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author trajk
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {
    private Zaposleni zaposleni;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Zaposleni)){
            throw new Exception("Sistem nije pronasao zaposlenog!");
        }
        Zaposleni z=(Zaposleni)param;
        
        for(Zaposleni zaposleni:Controller.getInstance().getUlogovani()){
            if(zaposleni.equals(z))
                throw new Exception("Zaposleni je vec ulogovan na sistem");
        }
       
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Zaposleni> sviZaposleni=broker.getAll((Zaposleni)param,null);
        for(Zaposleni z:sviZaposleni){
            System.out.println(z.getEmail()+ " "+z.getSifra());
            if(z.equals((Zaposleni)param)){
                Controller.getInstance().getUlogovani().add(z);
                zaposleni=z;
                return;
            }
        }
        throw new Exception("Korisnicko ime i sifra nisu ispravni");
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
    
}
