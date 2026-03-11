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
public class Zaposleni implements ApstraktniDomenskiObjekat {

    private int zaposleniID;
    private String ime;
    private String prezime;
    private String email;
    private String sifra;

    public Zaposleni() {
    }

    public Zaposleni(int zaposleniID, String ime, String prezime, String email, String sifra) {
        this.zaposleniID = zaposleniID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.sifra = sifra;
    }

    public int getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(int zaposleniID) {
        this.zaposleniID = zaposleniID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int zaposleniID = rs.getInt("zaposleni.zaposleniID");
            String ime = rs.getString("zaposleni.ime");
            String prezime = rs.getString("zaposleni.prezime");
            String email = rs.getString("zaposleni.email");
            String sifra = rs.getString("zaposleni.sifra");
            Zaposleni z = new Zaposleni(zaposleniID, ime, prezime, email, sifra);
            lista.add(z);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "email,ime,prezime,sifra";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + email + "', '" + ime + "', '" + prezime + "', '" + sifra + "'";
    }

    @Override
    public String vratiPrimarniKljic() {
        return "zaposleni.zaposleniID=" + zaposleniID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "email='" + email + "', ime='" + ime + "', prezime='" + prezime + "', sifra='" + sifra + "'";
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
