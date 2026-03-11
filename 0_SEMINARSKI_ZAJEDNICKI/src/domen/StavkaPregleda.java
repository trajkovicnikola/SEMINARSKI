package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StavkaPregleda implements ApstraktniDomenskiObjekat {

    private int pregledID;
    private int rb;
    private int kolicina;
    private double iznos;
    private double cena;
    private Tretman tretman;

    public StavkaPregleda() {
    }

    public StavkaPregleda(int pregledID, int rb, int kolicina, double iznos, double cena, Tretman tretman) {
        this.pregledID = pregledID;
        this.rb = rb;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.cena = cena;
        this.tretman = tretman;
    }

    public int getPregledID() {
        return pregledID;
    }

    public void setPregledID(int pregledID) {
        this.pregledID = pregledID;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Tretman getTretman() {
        return tretman;
    }

    public void setTretman(Tretman tretman) {
        this.tretman = tretman;
    }

    @Override
    public String toString() {
        return "Rb: " + rb + ", tretman: " + tretman + ", kolicina: " + kolicina + ", iznos: " + iznos + ", cena: " + cena;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pregledID, rb);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StavkaPregleda)) {
            return false;
        }
        StavkaPregleda other = (StavkaPregleda) obj;
        return this.pregledID == other.pregledID && this.rb == other.rb;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkapregleda";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int pregledID = rs.getInt("stavkapregleda.pregledID");
            int rb = rs.getInt("stavkapregleda.rb");
            int kolicina = rs.getInt("stavkapregleda.kolicina");
            double iznos = rs.getDouble("stavkapregleda.iznos");

            double cena = rs.getDouble("stavkapregleda.cena");

            int tretmanID = rs.getInt("tretman.tretmanID");
            String naziv = rs.getString("tretman.naziv");
            String opis = rs.getString("tretman.opis");
            double tretmanCena = rs.getDouble("tretman.cena");

            Tretman t = new Tretman(tretmanID, naziv, opis, tretmanCena);

            StavkaPregleda sp = new StavkaPregleda(pregledID, rb, kolicina, iznos, cena, t);
            lista.add(sp);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "pregledID, rb, kolicina, iznos, cena, tretmanID";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return pregledID + ", " + rb + ", " + kolicina + ", " + iznos + ", " + cena + ", " + tretman.getTretmanID();
    }

    @Override
    public String vratiPrimarniKljic() {
        return "pregledID = " + pregledID + " AND rb = " + rb;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        int pregledID = rs.getInt("pregledID");
        int rb = rs.getInt("rb");
        int kolicina = rs.getInt("kolicina");
        double iznos = rs.getDouble("iznos");
        double cena = rs.getDouble("cena");
        int tretmanID = rs.getInt("tretmanID");

        Tretman t = new Tretman();
        t.setTretmanID(tretmanID);

        return new StavkaPregleda(pregledID, rb, kolicina, iznos, cena, t);
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "pregledID = " + pregledID
                + ", rb = " + rb
                + ", kolicina = " + kolicina
                + ", iznos = " + iznos
                + ", cena = " + cena
                + ", tretmanID = " + tretman.getTretmanID();
    }

    @Override
    public String vratiJoin() {
        return "JOIN tretman ON stavkapregleda.tretmanID = tretman.tretmanID";
    }

    @Override
    public String vratiUslovZaSelect() {
        return "";
    }

    @Override
    public String vratiDodatniUslov() {
        return "stavkapregleda.pregledID=" + pregledID;
    }
    
    
}
