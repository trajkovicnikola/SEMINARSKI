package domen;

import org.junit.Test;
import static org.junit.Assert.*;

public class TretmanTest {

    @Test
    public void testKonstruktor() {
        Tretman t = new Tretman(1, "Masaza", "Relaksirajuca masaza", 1500.0);
        assertEquals(1, t.getTretmanID());
        assertEquals("Masaza", t.getNaziv());
        assertEquals("Relaksirajuca masaza", t.getOpis());
        assertEquals(1500.0, t.getCena(), 0.001);
    }

    @Test
    public void testSetteri() {
        Tretman t = new Tretman();
        t.setTretmanID(3);
        t.setNaziv("Akupunktura");
        t.setOpis("Tretman iglama");
        t.setCena(2000.0);
        assertEquals(3, t.getTretmanID());
        assertEquals("Akupunktura", t.getNaziv());
        assertEquals("Tretman iglama", t.getOpis());
        assertEquals(2000.0, t.getCena(), 0.001);
    }

    @Test
    public void testEqualsIstID() {
        Tretman t1 = new Tretman(1, "Masaza", "Opis", 1000.0);
        Tretman t2 = new Tretman(1, "Nesto drugo", "Drugi opis", 500.0);
        assertEquals(t1, t2);
    }

    @Test
    public void testEqualsDrugiID() {
        Tretman t1 = new Tretman(1, "Masaza", "Opis", 1000.0);
        Tretman t2 = new Tretman(2, "Masaza", "Opis", 1000.0);
        assertNotEquals(t1, t2);
    }

    @Test
    public void testToStringVracaNaziv() {
        Tretman t = new Tretman(1, "Masaza", "Opis", 1000.0);
        assertEquals("Masaza", t.toString());
    }

    @Test
    public void testVratiNazivTabele() {
        Tretman t = new Tretman();
        assertEquals("tretman", t.vratiNazivTabele());
    }

    @Test
    public void testVratiUslovZaSelectPrazanBezNaziva() {
        Tretman t = new Tretman();
        assertEquals("", t.vratiUslovZaSelect());
    }

    @Test
    public void testVratiUslovZaSelectSaNazivom() {
        Tretman t = new Tretman();
        t.setNaziv("masa");
        assertTrue(t.vratiUslovZaSelect().contains("masa"));
    }
}
