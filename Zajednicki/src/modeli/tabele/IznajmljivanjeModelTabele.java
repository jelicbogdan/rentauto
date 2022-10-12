/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli.tabele;

import domen.Automobil;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Korisnik;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class IznajmljivanjeModelTabele extends AbstractTableModel{
    
    private final List<Iznajmljivanje> lista;
    private String[] naziviKolona = new String[]{"ID","Automobil","Klijent", "Datum od","Datum do","Iznos","Cena", "Korisnik"};
    private Class[] naziviKlasa = new Class[]{ Long.class,Automobil.class, Klijent.class, Date.class, Date.class,Double.class,Double.class,Korisnik.class};

    public IznajmljivanjeModelTabele(List<Iznajmljivanje> lista) {
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
Iznajmljivanje iz = lista.get(rowIndex);
        switch (columnIndex) {
           
            case 0:
                return iz.getIznajmljivanjeId();
            case 1:
                return iz.getAutomobil();
            case 2:
                return iz.getKlijent();
            case 3:
                return iz.getDatumOd();
            case 4:
                return iz.getDatumDo();
            case 5:
                return iz.getIznos();  
            case 6:
                return iz.getCena();
            case 7:
                return iz.getKorisnik();
            default:
                return "n/a";
        }    }
     public Iznajmljivanje vratiRed(int row){
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
    public List<Iznajmljivanje> vratiRed(){
        return lista;
    }
    
}
