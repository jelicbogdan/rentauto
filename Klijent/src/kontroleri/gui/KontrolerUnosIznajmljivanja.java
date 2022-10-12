/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

import domen.Automobil;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Korisnik;
import gui.PanelUnosIznajmljivanja;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.tabele.IznajmljivanjeModelTabele;
import servis.Komunikacija;
import sesija.Sesija;

/**
 *
 * @author Lenovo
 */
class KontrolerUnosIznajmljivanja {

    private static KontrolerUnosIznajmljivanja instanca;
    private PanelUnosIznajmljivanja panel;
    private Iznajmljivanje iznIzmena; 
    private List<Iznajmljivanje> lista = new ArrayList();
    private List<Iznajmljivanje> listaSvi;

    private KontrolerUnosIznajmljivanja() {
    }
    
    static KontrolerUnosIznajmljivanja getInstance() {
        if (instanca == null) {
            instanca = new KontrolerUnosIznajmljivanja();
        }
        return instanca;    
    }
    
    
    public PanelUnosIznajmljivanja getPanel(Iznajmljivanje izn) {
       
        initializePanel();
        priprema();
        if(!(izn ==null)){
            this.iznIzmena = izn;
            postaviFalse();
            postaviVrednosti();
        }
        panel.setVisible(true);
        return panel;
    }

    private void initializePanel() {
       panel = new PanelUnosIznajmljivanja();
        addEventHandlers();
       
    } 

    private void addEventHandlers() {
        this.panel.getBtnDodaj().addActionListener(e -> dodaj());
        this.panel.getBtnObrisi().addActionListener(e -> obrisi());
        this.panel.getBtnSacuvaj().addActionListener(e -> sacuvaj());
        this.panel.getBtnIzmeni().addActionListener(e -> izmeni());
        this.panel.getBtnDozvoli().addActionListener(e -> dozvoliIzmenu());    }

    private void priprema() {
        lista = new ArrayList();
        postaviTabelu();
        panel.getBtnDozvoli().setVisible(false);
        panel.getBtnIzmeni().setVisible(false);
        postaviKlijente();
        postaviAutomobile();
        postaviIznSvi();    }

    private void postaviTabelu() {
        panel.getTblIznajmljivanje().setModel(new IznajmljivanjeModelTabele(lista));    }

    private void postaviKlijente() {
        try {
            List<Klijent> listaKl = Komunikacija.getInstance().VratiSveKlijente();
            for (Klijent klijent : listaKl) {
                panel.getCmbKlijent().addItem(klijent);
            }
        } catch (IOException ex) {
            Logger.getLogger(KontrolerUnosIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    private void postaviAutomobile() {
        try {
            List<Automobil> listaAutomobila = Komunikacija.getInstance().VratiSveAutomobile();
            for (Automobil a : listaAutomobila) {
                panel.getCmbAutomobil().addItem(a);
            }
        } catch (IOException ex) {
            Logger.getLogger(KontrolerUnosIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    private void postaviIznSvi() {
        try {
            listaSvi = Komunikacija.getInstance().VratiSvaIznajmljivanja();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerUnosIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    private void dodaj() {
         Iznajmljivanje izn = kreiraj();
        if(izn == null) return;
        boolean nasao = false;
        
        for(Iznajmljivanje i : listaSvi){
            if( i.getKlijent().getKlijentId() == izn.getKlijent().getKlijentId() && i.getAutomobil().getAutomobilID()== izn.getAutomobil().getAutomobilID()
                    && ( i.getDatumOd().equals(izn.getDatumOd()) 
                    || i.getDatumDo().equals(izn.getDatumDo()) )){
                JOptionPane.showMessageDialog(panel, "Klijent je već zakazao iznajmljivanje tog automobila u tom terminu.", "Greška", JOptionPane.ERROR_MESSAGE);
                 
                        nasao = true;
                        return;
            }else{
            if(i.getKlijent().getKlijentId() == izn.getKlijent().getKlijentId() 
                    && ( i.getDatumOd().equals(izn.getDatumOd()) 
                    || i.getDatumDo().equals(izn.getDatumDo()) )){
                    JOptionPane.showMessageDialog(panel,"Klijent već ima zakazano iznajmljivanje u tom terminu.", "Greška", JOptionPane.ERROR_MESSAGE);
                        nasao = true;
                        return;
            }
                    if ( i.getAutomobil().getAutomobilID()== izn.getAutomobil().getAutomobilID()
                    && (i.getDatumOd().equals(izn.getDatumOd()) 
                    || i.getDatumDo().equals(izn.getDatumDo()) )){
                JOptionPane.showMessageDialog(panel,"Za izabrani automobil već postoji zakazano iznajmljivanje u tom terminu.", "Greška", JOptionPane.ERROR_MESSAGE);
            nasao = true;
            return;
            }
        }
        }
        
        for(Iznajmljivanje i2 : lista){
            if( i2.getKlijent().getKlijentId() == izn.getKlijent().getKlijentId() && i2.getAutomobil().getAutomobilID()== izn.getAutomobil().getAutomobilID()
                    && ( i2.getDatumOd().equals(izn.getDatumOd()) 
                    || i2.getDatumDo().equals(izn.getDatumDo()) )){
                 JOptionPane.showMessageDialog(panel,"Klijent je već zakazao iznajmljivanje tog automobila u tom terminu.", "Greška", JOptionPane.ERROR_MESSAGE);
                        nasao = true;
                        return;
            }else{
            if(i2.getKlijent().getKlijentId() == izn.getKlijent().getKlijentId() 
                    && ( i2.getDatumOd().equals(izn.getDatumOd()) 
                    || i2.getDatumDo().equals(izn.getDatumDo()) )){
                    JOptionPane.showMessageDialog(panel,"Klijent već ima zakazano iznajmljivanje u tom terminu.", "Greška", JOptionPane.ERROR_MESSAGE);
                        nasao = true;
                        return;
            }
                    if ( i2.getAutomobil().getAutomobilID()== izn.getAutomobil().getAutomobilID()
                    && (i2.getDatumOd().equals(izn.getDatumOd()) 
                    || i2.getDatumDo().equals(izn.getDatumDo()) )){
                JOptionPane.showMessageDialog(panel,"Za izabrani automobil već postoji zakazano iznajmljivanje u tom terminu.", "Greška", JOptionPane.ERROR_MESSAGE);
            nasao = true;
            return;
            }
        }
        }
        
        
        
        
        
        if(!nasao){
        lista.add(izn);
        
        postaviTabelu();
    }
    }

    private boolean proveraPostojiIznajmljivanje(Iznajmljivanje i) {
 for (Iznajmljivanje iznajmljivanje : listaSvi) {
            if(iznajmljivanje.getAutomobil().getNaziv().equals(i.getAutomobil().getNaziv())){
                if((i.getDatumOd().after(iznajmljivanje.getDatumOd()) && i.getDatumOd().before(iznajmljivanje.getDatumDo()) ) || 
                       (i.getDatumDo().after(iznajmljivanje.getDatumOd()) && i.getDatumDo().before(iznajmljivanje.getDatumDo()) ) ||
                        (i.getDatumOd().before(iznajmljivanje.getDatumOd()) && i.getDatumDo().after(iznajmljivanje.getDatumDo()))){
                    return true;
                }
            }
        }
        for (Iznajmljivanje iznajmljivanje : lista) {
            if(iznajmljivanje.getAutomobil().getNaziv().equals(i.getAutomobil().getNaziv())){
                if((i.getDatumOd().after(iznajmljivanje.getDatumOd()) && i.getDatumOd().before(iznajmljivanje.getDatumDo()) ) || 
                       (i.getDatumDo().after(iznajmljivanje.getDatumOd()) && i.getDatumDo().before(iznajmljivanje.getDatumDo()) ) ||
                        (i.getDatumOd().before(iznajmljivanje.getDatumOd()) && i.getDatumDo().after(iznajmljivanje.getDatumDo()))){
                    return true;
                }
            }
        }
        return false;    }

    private void obrisi() {
        int row = panel.getTblIznajmljivanje().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(panel, "Niste izabrali iznajmljivanje.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Iznajmljivanje i = lista.get(row);
        lista.remove(i);
        postaviTabelu();    }

    private void sacuvaj() {
                try {
             if(lista.size() ==0){
                 JOptionPane.showMessageDialog(panel, "Morate uneti makar jedno iznajmljivanje.", "Greška", JOptionPane.ERROR_MESSAGE);
             
             return;
             }   
             boolean sig = Komunikacija.getInstance().ZapamtiIznajmljivanje(lista);
             if(sig){
                 JOptionPane.showMessageDialog(panel, "Sistem je uspešno izvršio čuvanje!", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
             }else{
                 JOptionPane.showMessageDialog(panel, "Sistem ne može da izvrši čuvanje!", "Greška", JOptionPane.ERROR_MESSAGE);
             }
         } catch (IOException ex) {
             Logger.getLogger(KontrolerUnosAutomobila.class.getName()).log(Level.SEVERE, null, ex);
         }    }

    private void izmeni() {
                try {
            Iznajmljivanje izn = kreiraj();
            if(izn == null) return;
            izn.setIznajmljivanjeId(iznIzmena.getIznajmljivanjeId());
            boolean sig = Komunikacija.getInstance().IzmeniIznajmljivanje(izn);
            if(sig){
                JOptionPane.showMessageDialog(panel, "Sistem je izvršio izmenu !", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(panel, "Sistem ne može da izvrši izmenu!", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(KontrolerUnosIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    private void dozvoliIzmenu() {
            postaviTrue();
    }

    private Iznajmljivanje kreiraj() {
 try{
            Date datumOd =new SimpleDateFormat("yyyy-MM-dd").parse(panel.getTxtDatumOd().getText());
            Date datumDo =new SimpleDateFormat("yyyy-MM-dd").parse(panel.getTxtDatumDo().getText());
            
            Automobil a =  (Automobil) panel.getCmbAutomobil().getSelectedItem();
            Klijent  k = (Klijent) panel.getCmbKlijent().getSelectedItem();
            if(!datumiProvera(datumOd, datumDo)) return null;
            long diff = datumDo.getTime() - datumOd.getTime();
            long dana = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            double iznos = dana * a.getCenaPoDanu();
            double cena = 25;
            Korisnik kor = Sesija.getInstance().getKorisnik();
            Iznajmljivanje i = new Iznajmljivanje(new Long(0), a, k, datumOd, datumDo, iznos, cena, kor);
         
            return i;
        }catch(Exception e){
            JOptionPane.showMessageDialog(panel, "Datum mora biti u formatu yyyy-MM-dd", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }    }

   

    private boolean datumiProvera(Date datumOd, Date datumDo) {
                Date sada =  java.sql.Date.valueOf(LocalDate.now());
           if(datumOd.before(sada) || datumDo.before(sada)){
               JOptionPane.showMessageDialog(panel, "Datum mora biti nakon današnjeg.", "Greška", JOptionPane.ERROR_MESSAGE);
               return false;
           }
           if(datumOd.after(datumDo)){
               JOptionPane.showMessageDialog(panel, "Datum do mora biti nakon datuma unetog u polju Datum od.", "Greška", JOptionPane.ERROR_MESSAGE);
               return false;
           }
           return true;    
    }

    private void postaviTrue() {
        panel.getBtnDozvoli().setEnabled(false);
        panel.getBtnIzmeni().setEnabled(true);
        panel.getBtnSacuvaj().setVisible(false);
        panel.getBtnDodaj().setVisible(false);
        panel.getBtnObrisi().setVisible(false);
        panel.getTblIznajmljivanje().setEnabled(false);
        panel.getTxtDatumOd().setEnabled(true);
        panel.getTxtDatumDo().setEnabled(true);
        panel.getCmbKlijent().setEnabled(true);
        panel.getCmbAutomobil().setEnabled(true);    }
    
    private void postaviFalse() {
        panel.getBtnDozvoli().setVisible(true);
        panel.getBtnIzmeni().setVisible(true);
        panel.getBtnIzmeni().setEnabled(false);
        panel.getBtnSacuvaj().setVisible(false);
        panel.getBtnDodaj().setVisible(false);
        panel.getBtnObrisi().setVisible(false);
        panel.getTblIznajmljivanje().setEnabled(false);
        panel.getTxtDatumOd().setEnabled(false);
        panel.getTxtDatumDo().setEnabled(false);
        panel.getCmbKlijent().setEnabled(false);
        panel.getCmbAutomobil().setEnabled(false);    }

    private void postaviVrednosti() {
        panel.getCmbKlijent().setSelectedItem(iznIzmena.getKlijent());
        panel.getCmbAutomobil().setSelectedItem(iznIzmena.getAutomobil());
        panel.getTxtDatumOd().setText(String.valueOf(iznIzmena.getDatumOd()));
        panel.getTxtDatumDo().setText(String.valueOf(iznIzmena.getDatumDo()));    }
}
