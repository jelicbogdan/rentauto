/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli.tabele;

import domen.Automobil;
import domen.Model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class AutomobilModelTabele extends AbstractTableModel{
    
    private final List<Automobil> lista;
    private String[] naziviKolona = new String[]{"AutomobilID","Naziv","Vlasnik","Opis","Cena po danu","Tip automobila"};
    private Class[] naziviKlasa = new Class[]{ Long.class,String.class, String.class, String.class,Double.class,Model.class};

    public AutomobilModelTabele (List<Automobil> lista) {
        this.lista = lista;
    }
    
    
    @Override
    public int getRowCount() {
   if (lista == null) {
            return 0;
        }
        return lista.size();
    }

    @Override
    public int getColumnCount() {
  return naziviKolona.length;    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    Automobil a = lista.get(rowIndex);
        switch (columnIndex) {
           
            case 0:
                return a.getAutomobilID();
            case 1:
                return a.getNaziv();
            case 2:
                return a.getVlasnik();
           
            case 3:
                return a.getOpis();
            case 4:
                return a.getCenaPoDanu();
            
             case 5:
                return a.getTipAutomobilaID().getNazivModela();
            default:
                return "n/a";
        }    }
    
    public Automobil vratiRed(int row){
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
