package forme.model;

import domen.StavkaPregleda;
import domen.Tretman;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelTabeleStavkaTest {

    private ModelTabeleStavka model;
    private Tretman tretman1;
    private Tretman tretman2;

    @Before
    public void setUp() {
        tretman1 = new Tretman(1, "Masaza", "Opis", 500.0);
        tretman2 = new Tretman(2, "Akupunktura", "Opis", 1000.0);
        model = new ModelTabeleStavka(new ArrayList<>());
    }

    @Test
    public void testPrazanModelNemaRedova() {
        assertEquals(0, model.getRowCount());
    }

    @Test
    public void testBrojKolona() {
        assertEquals(5, model.getColumnCount());
    }

    @Test
    public void testDodajStavkuPovecavaRowCount() {
        StavkaPregleda sp = napraviStavku(tretman1, 2);
        model.dodajStavku(sp);
        assertEquals(1, model.getRowCount());
    }

    @Test
    public void testDodajDveRazliciteStavke() {
        model.dodajStavku(napraviStavku(tretman1, 1));
        model.dodajStavku(napraviStavku(tretman2, 1));
        assertEquals(2, model.getRowCount());
    }

    @Test
    public void testDodajIsteTretmanSabiraPodatke() {
        StavkaPregleda sp1 = napraviStavku(tretman1, 2);
        StavkaPregleda sp2 = napraviStavku(tretman1, 3);
        model.dodajStavku(sp1);
        model.dodajStavku(sp2);
        // treba da ostane 1 red, sa kolicinom 5
        assertEquals(1, model.getRowCount());
        assertEquals(5, model.getLista().get(0).getKolicina());
    }

    @Test
    public void testDodajIsteTretmanIznos() {
        StavkaPregleda sp1 = napraviStavku(tretman1, 2); // 2 * 500 = 1000
        StavkaPregleda sp2 = napraviStavku(tretman1, 3); // 3 * 500 = 1500
        model.dodajStavku(sp1);
        model.dodajStavku(sp2);
        // 5 * 500 = 2500
        assertEquals(2500.0, model.getLista().get(0).getIznos(), 0.001);
    }

    @Test
    public void testObrisiStavku() {
        model.dodajStavku(napraviStavku(tretman1, 1));
        model.dodajStavku(napraviStavku(tretman2, 1));
        model.obrisiStavku(0);
        assertEquals(1, model.getRowCount());
    }

    @Test
    public void testObrisiStavkuAzuriraRb() {
        model.dodajStavku(napraviStavku(tretman1, 1));
        model.dodajStavku(napraviStavku(tretman2, 1));
        model.obrisiStavku(0);
        // preostala stavka treba da ima rb=1
        assertEquals(1, model.getLista().get(0).getRb());
    }

    @Test
    public void testDodajStavkuSetRb() {
        model.dodajStavku(napraviStavku(tretman1, 1));
        model.dodajStavku(napraviStavku(tretman2, 1));
        assertEquals(1, model.getLista().get(0).getRb());
        assertEquals(2, model.getLista().get(1).getRb());
    }

    @Test
    public void testGetValueAtKolicina() {
        StavkaPregleda sp = napraviStavku(tretman1, 3);
        model.dodajStavku(sp);
        assertEquals(3, model.getValueAt(0, 1));
    }

    @Test
    public void testGetValueAtCena() {
        StavkaPregleda sp = napraviStavku(tretman1, 2);
        model.dodajStavku(sp);
        assertEquals(500.0, model.getValueAt(0, 2));
    }

    @Test
    public void testGetValueAtIznos() {
        StavkaPregleda sp = napraviStavku(tretman1, 2); // 2 * 500 = 1000
        model.dodajStavku(sp);
        assertEquals(1000.0, model.getValueAt(0, 3));
    }

    @Test
    public void testGetValueAtTretman() {
        StavkaPregleda sp = napraviStavku(tretman1, 1);
        model.dodajStavku(sp);
        assertEquals(tretman1, model.getValueAt(0, 4));
    }

    @Test
    public void testNaziviKolona() {
        assertEquals("RB", model.getColumnName(0));
        assertEquals("Kolicina", model.getColumnName(1));
        assertEquals("Cena", model.getColumnName(2));
        assertEquals("Iznos", model.getColumnName(3));
        assertEquals("Tretman", model.getColumnName(4));
    }

    private StavkaPregleda napraviStavku(Tretman t, int kolicina) {
        StavkaPregleda sp = new StavkaPregleda();
        sp.setTretman(t);
        sp.setKolicina(kolicina);
        sp.setCena(t.getCena());
        sp.setIznos(kolicina * t.getCena());
        return sp;
    }
}
