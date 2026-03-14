package operacija.pregled;

import domen.ApstraktniDomenskiObjekat;
import domen.Pregled;
import domen.StavkaPregleda;
import java.util.Date;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

public class IzmeniPregledOperacija extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Pregled)) {
            throw new Exception("Sistem ne moze da izmeni pregled");
        }
        Pregled p = (Pregled) param;
        if (p.getPregledID() <= 0) {
            throw new Exception("Sistem ne moze da izmeni pregled");
        }
        if (p.getStavkePregleda() == null || p.getStavkePregleda().isEmpty()) {
            throw new Exception("Pregled mora imati bar jednu stavku pregleda");
        }
        if (p.getDatumPregleda() == null) {
            throw new Exception("Datum pregleda mora biti unesen");
        }
        Date sada = new Date();
        if (!p.getDatumPregleda().after(sada)) {
            throw new Exception("Datum pregleda mora biti u buducnosti");
        }
        if (!p.getDatumPregleda().after(p.getDatumRezervacije())) {
            throw new Exception("Datum pregleda mora biti posle datuma rezervacije");
        }
        List<Pregled> sviPregledi = broker.getAll(new Pregled(), null);
        for (Pregled postojeci : sviPregledi) {
            if (postojeci.getPregledID() == p.getPregledID()) {
                continue;
            }
            if (postojeci.getDatumPregleda().equals(p.getDatumPregleda())) {
                if (postojeci.getZaposleni().getZaposleniID() == p.getZaposleni().getZaposleniID()) {
                    throw new Exception("Zaposleni vec ima pregled u to vreme");
                }
                if (postojeci.getKlijent().getKlijentID() == p.getKlijent().getKlijentID()) {
                    throw new Exception("Klijent vec ima pregled u to vreme");
                }
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Pregled p = (Pregled) param;

        broker.edit(p);

        StavkaPregleda filter = new StavkaPregleda();
        filter.setPregledID(p.getPregledID());
        List<ApstraktniDomenskiObjekat> stareStavke = broker.getAll(filter, null);
        for (ApstraktniDomenskiObjekat stavka : stareStavke) {
            broker.delete((StavkaPregleda) stavka);
        }

        List<StavkaPregleda> noveStavke = p.getStavkePregleda();
        for (StavkaPregleda stavka : noveStavke) {
            stavka.setPregledID(p.getPregledID());
            broker.add(stavka);
        }
    }

}
