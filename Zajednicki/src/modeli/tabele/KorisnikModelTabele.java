/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli.tabele;

import domen.Korisnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class KorisnikModelTabele extends AbstractTableModel{
    
    private final List<Korisnik> lista;
    private String[] naziviKolona = new String[]{"Ime","Prezime","Korisnicko ime" };
    private Class[] naziviKlasa = new Class[]{ String.class, String.class, String.class};
    
    public KorisnikModelTabele(List<Korisnik> lista) {
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
 Korisnik kor = lista.get(rowIndex);
        switch (columnIndex) {
           
            case 0:
                return kor.getIme();
            case 1:
                return kor.getPrezime();
            case 2:
                return kor.getKorisnickoIme();
            
            default:
                return "n/a";
        }    }
    
    public Korisnik vratiRed(int row){
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
