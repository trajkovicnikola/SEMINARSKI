package forme.model;

import domen.Klijent;
import domen.Pregled;
import domen.Zaposleni;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelTabelePregledTest {

    private ModelTabelePregled model;
    private List<Pregled> lista;

    @Before
    public void setUp() {
        lista = new ArrayList<>();
        lista.add(napraviPregled(1, "Ana", "Ilic", "Marko", "Jovic"));
        lista.add(napraviPregled(2, "Petra", "Pavlic", "Nikola", "Trajkovic"));
        model = new ModelTabelePregled(lista);
    }

    @Test
    public void testRowCount() {
        assertEquals(2, model.getRowCount());
    }

    @Test
    public void testPraznaListaNemaRedova() {
        ModelTabelePregled prazan = new ModelTabelePregled(new ArrayList<>());
        assertEquals(0, prazan.getRowCount());
    }

    @Test
    public void testBrojKolona() {
        assertEquals(6, model.getColumnCount());
    }

    @Test
    public void testGetValueAtID() {
        assertEquals(1, model.getValueAt(0, 0));
        assertEquals(2, model.getValueAt(1, 0));
    }

    @Test
    public void testGetValueAtUkupanIznos() {
        assertEquals(3000.0, model.getValueAt(0, 5));
    }

    @Test
    public void testGetLista() {
        assertEquals(2, model.getLista().size());
        assertEquals(lista, model.getLista());
    }

    @Test
    public void testSetLista() {
        List<Pregled> novaLista = new ArrayList<>();
        novaLista.add(napraviPregled(3, "Jovana", "Milic", "Stefan", "Petrovic"));
        model.setLista(novaLista);
        assertEquals(1, model.getRowCount());
    }

    @Test
    public void testNaziviKolona() {
        assertEquals("ID", model.getColumnName(0));
        assertEquals("Zaposleni", model.getColumnName(1));
        assertEquals("Klijent", model.getColumnName(2));
        assertEquals("Datum rezervacije", model.getColumnName(3));
        assertEquals("Datum pregleda", model.getColumnName(4));
        assertEquals("Ukupan iznos", model.getColumnName(5));
    }

    private Pregled napraviPregled(int id, String imeKlijenta, String prezimeKlijenta,
            String imeZaposlenog, String prezimeZaposlenog) {
        Zaposleni z = new Zaposleni();
        z.setIme(imeZaposlenog);
        z.setPrezime(prezimeZaposlenog);

        Klijent k = new Klijent();
        k.setIme(imeKlijenta);
        k.setPrezime(prezimeKlijenta);

        return new Pregled(id, z, k, new Date(), new Date(), 3000.0, new ArrayList<>());
    }
}
