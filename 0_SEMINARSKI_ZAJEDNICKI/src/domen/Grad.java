/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author trajk
 */
public class Grad implements ApstraktniDomenskiObjekat {

    private int gradID;
    private String naziv;
    private String postanskiBroj;

    public Grad() {
    }

    public Grad(int gradID, String naziv, String postanskiBroj) {
        this.gradID = gradID;
        this.naziv = naziv;
        this.postanskiBroj = postanskiBroj;
    }

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grad other = (Grad) obj;
        if (this.gradID != other.gradID) {
            return false;
        }
        return Objects.equals(this.postanskiBroj, other.postanskiBroj);
    }

    @Override
    public String vratiNazivTabele() {
        return "grad";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int gradID = rs.getInt("grad.gradID");
            String naziv = rs.getString("grad.naziv");
            String postanskiBroj = rs.getString("grad.postanskiBroj");

            Grad g = new Grad(gradID, naziv, postanskiBroj);
            lista.add(g);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,postanskiBroj";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + naziv + "', '" + postanskiBroj + "'";
    }

    @Override
    public String vratiPrimarniKljic() {
        return "grad.gradID=" + gradID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', postanskiBroj='" + postanskiBroj + "'";
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public String vratiUslovZaSelect() {
        return "";
    }

    @Override
    public String vratiDodatniUslov() {
        return "";
    }

    

}
