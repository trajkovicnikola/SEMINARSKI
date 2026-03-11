/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Zaposleni;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author trajk
 */
public class ModelTabeleZaposleni extends AbstractTableModel {
    
    private List<Zaposleni> lista;
    private String[] kolone={"ID","Ime","Prezime","Mail"};

    public ModelTabeleZaposleni(List<Zaposleni> lista) {
        this.lista = lista;
    }

    public List<Zaposleni> getLista() {
        return lista;
    }

    public void setLista(List<Zaposleni> lista) {
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
        Zaposleni z=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return z.getZaposleniID();
            case 1:
                return z.getIme();
            case 2:
                return z.getPrezime();
            case 3:
                return z.getEmail();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
}
