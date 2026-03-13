package domen;

import org.junit.Test;
import static org.junit.Assert.*;

public class StavkaPregledaTest {

    private StavkaPregleda napraviStavku(int pregledID, int rb, int kolicina, double cena) {
        Tretman t = new Tretman(1, "Pregled", "Opsti pregled", cena);
        return new StavkaPregleda(pregledID, rb, kolicina, kolicina * cena, cena, t);
    }

    @Test
    public void testIznosJeKolicinaPutaCena() {
        StavkaPregleda sp = napraviStavku(1, 1, 3, 500.0);
        assertEquals(1500.0, sp.getIznos(), 0.001);
    }

    @Test
    public void testIznosJednaStavka() {
        StavkaPregleda sp = napraviStavku(1, 1, 1, 1200.0);
        assertEquals(1200.0, sp.getIznos(), 0.001);
    }

    @Test
    public void testEqualsIstaStavka() {
        Tretman t = new Tretman(1, "Pregled", "Opis", 500.0);
        StavkaPregleda sp1 = new StavkaPregleda(1, 1, 2, 1000.0, 500.0, t);
        StavkaPregleda sp2 = new StavkaPregleda(1, 1, 2, 1000.0, 500.0, t);
        assertEquals(sp1, sp2);
    }

    @Test
    public void testEqualsDrugiPregledID() {
        Tretman t = new Tretman(1, "Pregled", "Opis", 500.0);
        StavkaPregleda sp1 = new StavkaPregleda(1, 1, 2, 1000.0, 500.0, t);
        StavkaPregleda sp2 = new StavkaPregleda(2, 1, 2, 1000.0, 500.0, t);
        assertNotEquals(sp1, sp2);
    }

    @Test
    public void testEqualsDrugiRb() {
        Tretman t = new Tretman(1, "Pregled", "Opis", 500.0);
        StavkaPregleda sp1 = new StavkaPregleda(1, 1, 2, 1000.0, 500.0, t);
        StavkaPregleda sp2 = new StavkaPregleda(1, 2, 2, 1000.0, 500.0, t);
        assertNotEquals(sp1, sp2);
    }

    @Test
    public void testGetteri() {
        Tretman t = new Tretman(5, "Masaza", "Opis", 800.0);
        StavkaPregleda sp = new StavkaPregleda(10, 3, 2, 1600.0, 800.0, t);
        assertEquals(10, sp.getPregledID());
        assertEquals(3, sp.getRb());
        assertEquals(2, sp.getKolicina());
        assertEquals(1600.0, sp.getIznos(), 0.001);
        assertEquals(800.0, sp.getCena(), 0.001);
        assertEquals(t, sp.getTretman());
    }

    @Test
    public void testSetteri() {
        StavkaPregleda sp = new StavkaPregleda();
        sp.setPregledID(7);
        sp.setRb(2);
        sp.setKolicina(4);
        sp.setCena(300.0);
        sp.setIznos(1200.0);
        assertEquals(7, sp.getPregledID());
        assertEquals(2, sp.getRb());
        assertEquals(4, sp.getKolicina());
        assertEquals(300.0, sp.getCena(), 0.001);
        assertEquals(1200.0, sp.getIznos(), 0.001);
    }

    @Test
    public void testVratiNazivTabele() {
        StavkaPregleda sp = new StavkaPregleda();
        assertEquals("stavkapregleda", sp.vratiNazivTabele());
    }
}
