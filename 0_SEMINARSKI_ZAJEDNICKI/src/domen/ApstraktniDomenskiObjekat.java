/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author trajk
 */
public interface ApstraktniDomenskiObjekat extends Serializable {
    
    public String vratiNazivTabele();
    
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    
    public String vratiKoloneZaUbacivanje();
    
    public String vratiVrednostZaUbacivanje();
    
    public String vratiPrimarniKljic();
    
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    
    public String vratiVrednostiZaIzmenu();
    
    public String vratiJoin();
    
    public String vratiUslovZaSelect();
    
    public String vratiDodatniUslov();
    
}
