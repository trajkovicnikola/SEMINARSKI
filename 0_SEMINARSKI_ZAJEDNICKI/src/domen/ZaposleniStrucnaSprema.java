/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ZaposleniStrucnaSprema implements ApstraktniDomenskiObjekat {

    private Zaposleni zaposleni;
    private StrucnaSprema strucnaSprema;
    private Date datumKvalifikacije;

    public ZaposleniStrucnaSprema() {
    }

    public ZaposleniStrucnaSprema(Zaposleni zaposleni, StrucnaSprema strucnaSprema, Date datumKvalifikacije) {
        this.zaposleni = zaposleni;
        this.strucnaSprema = strucnaSprema;
        this.datumKvalifikacije = datumKvalifikacije;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public StrucnaSprema getStrucnaSprema() {
        return strucnaSprema;
    }

    public void setStrucnaSprema(StrucnaSprema strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }

    public Date getDatumKvalifikacije() {
        return datumKvalifikacije;
    }

    public void setDatumKvalifikacije(Date datumKvalifikacije) {
        this.datumKvalifikacije = datumKvalifikacije;
    }

    @Override
    public String toString() {
        return zaposleni + " - " + strucnaSprema;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zaposleni, strucnaSprema);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZaposleniStrucnaSprema)) {
            return false;
        }
        ZaposleniStrucnaSprema other = (ZaposleniStrucnaSprema) obj;
        return Objects.equals(this.zaposleni, other.zaposleni)
                && Objects.equals(this.strucnaSprema, other.strucnaSprema);
    }

    @Override
    public String vratiNazivTabele() {
        return "zapss";
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

            int strucnaSpremaID = rs.getInt("strucnasprema.strucnaSpremaID");
            String naziv = rs.getString("strucnasprema.naziv");

            StrucnaSprema ss = new StrucnaSprema(strucnaSpremaID, naziv);

            Date datumKvalifikacije = rs.getDate("zapss.datumKvalifikacije");
            ZaposleniStrucnaSprema zapss = new ZaposleniStrucnaSprema(z, ss, datumKvalifikacije);
            lista.add(zapss);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "zaposleniID, strucnaSpremaID, datumKvalifikacije";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return zaposleni.getZaposleniID() + ", " + strucnaSprema.getStrucnaSpremaID()
                + ", '" + datumKvalifikacije + "'";
    }

    @Override
    public String vratiPrimarniKljic() {
        return "zapss.zaposleniID=" + zaposleni.getZaposleniID()
                + " AND zapss.strucnaSpremaID=" + strucnaSprema.getStrucnaSpremaID();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        int zaposleniID = rs.getInt("zaposleniID");
        int strucnaSpremaID = rs.getInt("strucnaSpremaID");

        Zaposleni z = new Zaposleni();
        z.setZaposleniID(zaposleniID);

        StrucnaSprema ss = new StrucnaSprema();
        ss.setStrucnaSpremaID(strucnaSpremaID);

        return new ZaposleniStrucnaSprema(z, ss, null);
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "zaposleniID=" + zaposleni.getZaposleniID()
                + ", strucnaSpremaID=" + strucnaSprema.getStrucnaSpremaID()
                + ", datumKvalifikacije='" + datumKvalifikacije + "'";
    }

    @Override
    public String vratiJoin() {
        return "JOIN zaposleni ON zapss.zaposleniID = zaposleni.zaposleniID "
                + "JOIN strucnasprema ON zapss.strucnaSpremaID = strucnasprema.strucnaSpremaID";
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
