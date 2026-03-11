/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Pregled;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *
 * @author trajk
 */
public class ModelTabelePregled extends AbstractTableModel {

    private List<Pregled> lista;
    private String[] kolone = {"ID", "Zaposleni", "Klijent", "Datum rezervacije", "Datum pregleda","Ukupan iznos"};

    public ModelTabelePregled(List<Pregled> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pregled p = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("d.M.yyyy HH:mm");
        switch (columnIndex) {
            case 0:
                return p.getPregledID();
            case 1:
                return p.getZaposleni();
            case 2:
                return p.getKlijent();
            case 3:
                return sdf.format(p.getDatumRezervacije());
            case 4:
                return sdf.format(p.getDatumPregleda());
            case 5:
                return p.getUkupanIznos();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Pregled> getLista() {
        return lista;
    }

    public void setLista(List<Pregled> lista) {
        this.lista = lista;
    }

//    public void pretraziPoDatumu(Date izabraniDatum) {
//        Calendar trazeni = Calendar.getInstance();
//        trazeni.setTime(izabraniDatum);
//
//        List<Pregled> filterovana = lista.stream()
//                .filter(p -> {
//                    Calendar datumPregleda = Calendar.getInstance();
//                    datumPregleda.setTime(p.getDatumPregleda());
//
//                    return datumPregleda.get(Calendar.YEAR) == trazeni.get(Calendar.YEAR)
//                            && datumPregleda.get(Calendar.MONTH) == trazeni.get(Calendar.MONTH)
//                            && datumPregleda.get(Calendar.DAY_OF_MONTH) == trazeni.get(Calendar.DAY_OF_MONTH);
//                })
//                .collect(Collectors.toList());
//        lista=filterovana;
//        fireTableDataChanged();
//    }

}
