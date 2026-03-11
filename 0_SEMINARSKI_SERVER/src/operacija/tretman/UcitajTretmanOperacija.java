/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.tretman;

import domen.Pregled;
import domen.Tretman;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author trajk
 */
public class UcitajTretmanOperacija extends ApstraktnaGenerickaOperacija{
    
    List<Tretman> lista;

    public List<Tretman> getLista() {
        return lista;
    }

    public void setLista(List<Tretman> lista) {
        this.lista = lista;
    }
    
    

    @Override
    protected void preduslovi(Object param) throws Exception {
         if(param==null || !(param instanceof Tretman)){
            throw new Exception("Sistem ne moze da ucita tretmane");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        lista=broker.getAll((Tretman)param,null);
    }
    
}
