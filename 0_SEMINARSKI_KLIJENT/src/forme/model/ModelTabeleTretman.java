/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Tretman;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author trajk
 */
public class ModelTabeleTretman extends AbstractTableModel {
    
    private List<Tretman> lista;
    private String[] kolone={"ID","Naziv","Opis","Cena"};

    public ModelTabeleTretman(List<Tretman> lista) {
        this.lista = lista;
    }

    public List<Tretman> getLista() {
        return lista;
    }

    public void setLista(List<Tretman> lista) {
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
        Tretman t=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getTretmanID();
            case 1:
                return t.getNaziv();
            case 2:
                return t.getOpis();
            case 3:
                return t.getCena();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
}
