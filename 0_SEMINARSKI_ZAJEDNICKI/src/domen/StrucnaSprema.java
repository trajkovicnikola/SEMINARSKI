/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StrucnaSprema implements ApstraktniDomenskiObjekat {

    private int strucnaSpremaID;
    private String naziv;

    public StrucnaSprema() {
    }

    public StrucnaSprema(int strucnaSpremaID, String naziv) {
        this.strucnaSpremaID = strucnaSpremaID;
        this.naziv = naziv;
    }

    public int getStrucnaSpremaID() {
        return strucnaSpremaID;
    }

    public void setStrucnaSpremaID(int strucnaSpremaID) {
        this.strucnaSpremaID = strucnaSpremaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strucnaSpremaID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StrucnaSprema)) {
            return false;
        }
        StrucnaSprema other = (StrucnaSprema) obj;
        return this.strucnaSpremaID == other.strucnaSpremaID;
    }

    @Override
    public String vratiNazivTabele() {
        return "strucnasprema";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int strucnaSpremaID = rs.getInt("strucnasprema.strucnaSpremaID");
            String naziv = rs.getString("strucnasprema.naziv");

            StrucnaSprema ss = new StrucnaSprema(strucnaSpremaID, naziv);
            lista.add(ss);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + naziv + "'";
    }

    @Override
    public String vratiPrimarniKljic() {
        return "strucnasprema.strucnaSpremaID=" + strucnaSpremaID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        int strucnaSpremaID = rs.getInt("strucnaSpremaID");
        String naziv = rs.getString("naziv");

        return new StrucnaSprema(strucnaSpremaID, naziv);
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "'";
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
