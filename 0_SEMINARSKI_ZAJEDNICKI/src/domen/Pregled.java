package domen;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Pregled implements ApstraktniDomenskiObjekat {

    private int pregledID;
    private Zaposleni zaposleni;
    private Klijent klijent;
    private Date datumRezervacije;
    private Date datumPregleda;
    private double ukupanIznos;
    private List<StavkaPregleda> stavkePregleda;

    public Pregled() {
    }

    public Pregled(int pregledID, Zaposleni zaposleni, Klijent klijent, Date datumRezervacije, Date datumPregleda, double ukupanIznos, List<StavkaPregleda> stavkePregleda) {
        this.pregledID = pregledID;
        this.zaposleni = zaposleni;
        this.klijent = klijent;
        this.datumRezervacije = datumRezervacije;
        this.datumPregleda = datumPregleda;
        this.ukupanIznos = ukupanIznos;
        this.stavkePregleda = stavkePregleda;
    }

    public int getPregledID() {
        return pregledID;
    }

    public void setPregledID(int pregledID) {
        this.pregledID = pregledID;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Date getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(Date datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public Date getDatumPregleda() {
        return datumPregleda;
    }

    public void setDatumPregleda(Date datumPregleda) {
        this.datumPregleda = datumPregleda;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public List<StavkaPregleda> getStavkePregleda() {
        return stavkePregleda;
    }

    public void setStavkePregleda(List<StavkaPregleda> stavkePregleda) {
        this.stavkePregleda = stavkePregleda;
    }

    @Override
    public String toString() {
        return "Zaposleni: " + zaposleni + ", Klijent: " + klijent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pregledID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pregled)) {
            return false;
        }
        Pregled other = (Pregled) obj;
        return this.pregledID == other.pregledID;
    }

    @Override
    public String vratiNazivTabele() {
        return "pregled";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int pregledID = rs.getInt("pregled.pregledID");

            Timestamp datumRezervacijeSQL = rs.getTimestamp("pregled.datumRezervacije");
            Date datumRezervacije = new Date(datumRezervacijeSQL.getTime());

            Timestamp datumPregledaSQL = rs.getTimestamp("pregled.datumPregleda");
            Date datumPregleda = new Date(datumPregledaSQL.getTime());

            double ukupanIznos = rs.getDouble("pregled.ukupanIznos");

            int zaposleniID = rs.getInt("zaposleni.zaposleniID");
            String imeZaposlenog = rs.getString("zaposleni.ime");
            String prezimeZaposlenog = rs.getString("zaposleni.prezime");
            String email = rs.getString("zaposleni.email");
            String sifra = rs.getString("zaposleni.sifra");

            Zaposleni z = new Zaposleni(zaposleniID, imeZaposlenog, prezimeZaposlenog, email, sifra);

            int klijentID = rs.getInt("klijent.klijentID");
            String imeKlijenta = rs.getString("klijent.ime");
            String prezimeKlijenta = rs.getString("klijent.prezime");
            String telefon = rs.getString("klijent.telefon");

            int gradID = rs.getInt("grad.gradID");
            String nazivGrada = rs.getString("grad.naziv");
            String postanskiBroj = rs.getString("grad.postanskiBroj");

            Grad g = new Grad(gradID, nazivGrada, postanskiBroj);
            Klijent k = new Klijent(klijentID, imeKlijenta, prezimeKlijenta, telefon, g);

            Pregled p = new Pregled(pregledID, z, k, datumRezervacije, datumPregleda, ukupanIznos, new ArrayList<>());
            lista.add(p);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumRezervacije, datumPregleda, ukupanIznos, zaposleniID, klijentID";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "'" + sdf.format(datumRezervacije) + "', '"
                + sdf.format(datumPregleda) + "', "
                + ukupanIznos + ", "
                + zaposleni.getZaposleniID() + ", "
                + klijent.getKlijentID();
    }

    @Override
    public String vratiPrimarniKljic() {
        return "pregledID = " + pregledID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        int pregledID = rs.getInt("pregledID");

        Timestamp datumRezervacijeSQL = rs.getTimestamp("datumRezervacije");
        Date datumRezervacije = new Date(datumRezervacijeSQL.getTime());

        Timestamp datumPregledaSQL = rs.getTimestamp("datumPregleda");
        Date datumPregleda = new Date(datumPregledaSQL.getTime());

        double ukupanIznos = rs.getDouble("ukupanIznos");

        Pregled p = new Pregled();
        p.setPregledID(pregledID);
        p.setDatumRezervacije(datumRezervacije);
        p.setDatumPregleda(datumPregleda);
        p.setUkupanIznos(ukupanIznos);

        return p;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "datumRezervacije = '" + sdf.format(datumRezervacije) + "', "
                + "datumPregleda = '" + sdf.format(datumPregleda) + "', "
                + "ukupanIznos = " + ukupanIznos + ", "
                + "zaposleniID = " + zaposleni.getZaposleniID() + ", "
                + "klijentID = " + klijent.getKlijentID();
    }

    @Override
    public String vratiJoin() {
        return "JOIN zaposleni ON pregled.zaposleniID = zaposleni.zaposleniID "
                + "JOIN klijent ON pregled.klijentID = klijent.klijentID "
                + "JOIN grad ON klijent.gradID = grad.gradID";
    }

    @Override
    public String vratiUslovZaSelect() {
        if (datumPregleda == null) {
            return "";
        }

        java.sql.Date sqlDate = new java.sql.Date(datumPregleda.getTime());
        return " CAST(pregled.datumPregleda AS DATE) = '" + sqlDate + "'";
    }

    @Override
    public String vratiDodatniUslov() {
        return "";
    }

}
