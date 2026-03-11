/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;


import domen.Grad;
import domen.Klijent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author trajk
 */
public class ModelTabeleKlijent extends AbstractTableModel {
    
    List<Klijent> listaKlijenata;
    String[] kolone={"ID","Ime","Prezime","Telefon", "Grad"};

    public ModelTabeleKlijent(List<Klijent> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
    }
    
    
    @Override
    public int getRowCount() {
        return listaKlijenata.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k=listaKlijenata.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getKlijentID();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getTelefon();
            case 4:
                return k.getGrad();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Klijent> getListaKlijenata() {
        return listaKlijenata;
    }

    public void setListaKlijenata(List<Klijent> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
    }

//    public void pretrazi(String ime, String prezime, String telefon, Grad grad) {
//        List<Klijent> filterovana=listaKlijenata.stream()
//                .filter(k->(ime==null || ime.isEmpty() || k.getIme().toLowerCase().contains(ime.toLowerCase())))
//                .filter(k->(prezime==null || prezime.isEmpty() || k.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
//                .filter(k->(telefon==null || telefon.isEmpty() || k.getTelefon().contains(telefon)))
//                .filter(k -> grad == null || k.getGrad().equals(grad))
//                .collect(Collectors.toList());
//        listaKlijenata=filterovana;
//        fireTableDataChanged();
//    }
//    
    
    
}
