/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.klijent;

import domen.Klijent;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author trajk
 */
public class UcitajKlijenteOperacija extends ApstraktnaGenerickaOperacija {
    
    private List<Klijent> lista;

    @Override
    protected void preduslovi(Object param) throws Exception {
         if(param==null || !(param instanceof Klijent)){
            throw new Exception("Sistem ne moze da ucita klijente");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        lista=broker.getAll((Klijent) param, null);
    }

    public List<Klijent> getLista() {
        return lista;
    }

    public void setLista(List<Klijent> lista) {
        this.lista = lista;
    }
    
}
