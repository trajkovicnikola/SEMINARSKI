/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaPregleda;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author trajk
 */
public class ModelTabeleStavka extends AbstractTableModel {
    
    private List<StavkaPregleda> lista;
    private String[] kolone={"RB","Kolicina", "Cena", "Iznos", "Tretman"};

    public ModelTabeleStavka(List<StavkaPregleda> lista) {
        this.lista = lista;
    }

    public List<StavkaPregleda> getLista() {
        return lista;
    }

    public void setLista(List<StavkaPregleda> lista) {
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
        StavkaPregleda sp=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sp.getRb();
            case 1:
                return sp.getKolicina();
            case 2:
                return sp.getCena();
            case 3:
                return sp.getIznos();
            case 4:
                return sp.getTretman();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajStavku(StavkaPregleda sp) {
        int rb=lista.size()+1;
        sp.setRb(rb);
        lista.add(sp);
        fireTableDataChanged();
    }

    public void obrisiStavku(int selektovaniRed) {
        lista.remove(selektovaniRed);
        for(int i=0;i<lista.size();i++){
            lista.get(i).setRb(i+1);
        }
        fireTableDataChanged();
    }
    
    
    
}
