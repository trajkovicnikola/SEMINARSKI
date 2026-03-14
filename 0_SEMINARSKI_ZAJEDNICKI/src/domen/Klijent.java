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
public class Klijent implements ApstraktniDomenskiObjekat {

    private int klijentID;
    private String ime;
    private String prezime;
    private String telefon;
    private Grad grad;

    public Klijent() {
    }

    public Klijent(int klijentID, String ime, String prezime, String telefon, Grad grad) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.grad = grad;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Klijent other = (Klijent) obj;
        return this.klijentID == other.klijentID;
    }

    

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int klijentID = rs.getInt("klijent.klijentID");
            String ime = rs.getString("klijent.ime");
            String prezime = rs.getString("klijent.prezime");
            String telefon = rs.getString("klijent.telefon");
            String naziv = rs.getString("grad.naziv");
            String postanskiBroj = rs.getString("grad.postanskiBroj");
            int gradID = rs.getInt("grad.gradID");
            Grad g = new Grad(gradID, naziv, postanskiBroj);
            Klijent k = new Klijent(klijentID, ime, prezime, telefon, g);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,telefon, gradID";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + telefon + "', " + grad.getGradID();
    }

    @Override
    public String vratiPrimarniKljic() {
        return "klijent.klijentID=" + klijentID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', telefon = '" + telefon + "', gradID = " + grad.getGradID();
    }

    @Override
    public String vratiJoin() {
        return "JOIN grad ON klijent.gradID = grad.gradID";
    }

    @Override
    public String vratiUslovZaSelect() {
        String uslov = "";

        if (ime != null && !ime.trim().isEmpty()) {
            uslov += " klijent.ime LIKE '%" + ime.trim() + "%'";
        }

        if (prezime != null && !prezime.trim().isEmpty()) {
            if (!uslov.isEmpty()) {
                uslov += " AND ";
            }
            uslov += " klijent.prezime LIKE '%" + prezime.trim() + "%'";
        }

        if (grad != null && grad.getGradID() > 0) {
            if (!uslov.isEmpty()) {
                uslov += " AND ";
            }
            uslov += " grad.gradID = " + grad.getGradID();
        }

        if (uslov.isEmpty()) {
            return "";
        }

        return uslov;
    }

    @Override
    public String vratiDodatniUslov() {
        return "";
    }

}
