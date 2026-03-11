/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.pregled;

import domen.Pregled;
import domen.StavkaPregleda;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author trajk
 */
public class UcitajPregledOperacija extends ApstraktnaGenerickaOperacija {
    
    private List<Pregled> lista;

    @Override
    protected void preduslovi(Object param) throws Exception {
         if(param==null || !(param instanceof Pregled)){
            throw new Exception("Sistem ne moze da ucita preglede");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        lista=new ArrayList<>();
        List<Pregled> pregledi=broker.getAll((Pregled)param, null);
        for(Pregled p:pregledi){
            StavkaPregleda sp=new StavkaPregleda();
            sp.setPregledID(p.getPregledID());
            List<StavkaPregleda> stavke=broker.getAll(sp, null);
            p.setStavkePregleda(stavke);
            lista.add(p);
        }
    }

    public List<Pregled> getLista() {
        return lista;
    }

    public void setLista(List<Pregled> lista) {
        this.lista = lista;
    }
    
}
