/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trajk
 */
public class Tretman implements ApstraktniDomenskiObjekat {

    private int tretmanID;
    private String naziv;
    private String opis;
    private double cena;

    public Tretman() {
    }

    public Tretman(int tretmanID, String naziv, String opis, double cena) {
        this.tretmanID = tretmanID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public int getTretmanID() {
        return tretmanID;
    }

    public void setTretmanID(int tretmanID) {
        this.tretmanID = tretmanID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
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
        final Tretman other = (Tretman) obj;
        return this.tretmanID == other.tretmanID;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "tretman";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int tretmanID = rs.getInt("tretman.tretmanID");
            String naziv = rs.getString("tretman.naziv");
            String opis = rs.getString("tretman.opis");
            double cena = rs.getDouble("tretman.cena");

            Tretman t = new Tretman(tretmanID, naziv, opis, cena);
            lista.add(t);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,opis,cena";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + naziv + "', '" + opis + "', " + cena;
    }

    @Override
    public String vratiPrimarniKljic() {
        return "tretman.tretmanID=" + tretmanID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', opis='" + opis + "', cena=" + cena;
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public String vratiUslovZaSelect() {
        String uslov = "";

        if (naziv != null && !naziv.trim().isEmpty()) {
            uslov += " tretman.naziv LIKE '%" + naziv.trim() + "%'";
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
