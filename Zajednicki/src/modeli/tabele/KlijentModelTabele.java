/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli.tabele;

import domen.Klijent;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class KlijentModelTabele extends AbstractTableModel{
    
      
    private final List<Klijent> lista;
    private String[] naziviKolona = new String[]{"JMBG","Ime", "Drzava"};
    private Class[] naziviKlasa = new Class[]{ String.class, String.class, String.class};

    public KlijentModelTabele(List<Klijent> lista) {
        this.lista = lista;
    }
    @Override
    public int getRowCount() {
    if (lista == null) {
            return 0;
        }
        return lista.size();    }

    @Override
    public int getColumnCount() {
    return naziviKolona.length;    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    Klijent kli = lista.get(rowIndex);
        switch (columnIndex) {
           
            case 0:
                return kli.getJmbg();
            case 1:
                return kli.getIme();
            case 2:
                return kli.getDrzava();
            default:
                return "n/a";
        }    }
    public Klijent vratiRed(int row){
        return lista.get(row); 
    }
     public String getColumnName(int column) {
        if (column > naziviKolona.length) {
            return "n/a";
        }
        return naziviKolona[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return naziviKlasa[column];
    } 
    
}
