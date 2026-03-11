/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.grad;

import domen.Grad;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author trajk
 */
public class UcitajGradoveOperacija extends ApstraktnaGenerickaOperacija {
    
    private List<Grad> lista;

    @Override
    protected void preduslovi(Object param) throws Exception {
         if(param==null || !(param instanceof Grad)){
            throw new Exception("Sistem ne moze da ucita gradove");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        lista=broker.getAll((Grad) param,null);
    }

    public List<Grad> getLista() {
        return lista;
    }

    public void setLista(List<Grad> lista) {
        this.lista = lista;
    }
    
    
    
}
