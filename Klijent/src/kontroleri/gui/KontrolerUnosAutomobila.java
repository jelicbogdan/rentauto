/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

import domen.Automobil;
import domen.Iznajmljivanje;
import domen.Model;
import gui.PanelUnosAutomobila;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import servis.Komunikacija;

/**
 *
 * @author Lenovo
 */
class KontrolerUnosAutomobila {

    
    private static KontrolerUnosAutomobila instanca;
    private PanelUnosAutomobila panel;
    private List<Iznajmljivanje> lista = new ArrayList();
    Automobil izmena;

    public KontrolerUnosAutomobila() {
    }

    
    static KontrolerUnosAutomobila getInstance() {
        if (instanca == null) {
            instanca = new KontrolerUnosAutomobila();
        }
        return instanca;
    }
    
     public PanelUnosAutomobila getPanel(Automobil a) {
       
        initializePanel();
        priprema();
        if(!(a ==null)){
            this.izmena = a;
            postaviFalse();
            postaviVrednosti();
        }
        panel.setVisible(true);
        return panel;
    }

    private void initializePanel() {
       panel = new PanelUnosAutomobila();
        addEventHandlers();
       
    
}

    private void addEventHandlers() {
        this.panel.getBtnSacuvaj().addActionListener(e -> sacuvaj());
        this.panel.getBtnIzmeni().addActionListener(e -> izmeni());
        this.panel.getBtnDozvoli().addActionListener(e -> dozvoliIzmenu());
    }

    private void priprema() {
        panel.getBtnIzmeni().setVisible(false);
        panel.getBtnIzmeni().setEnabled(false);
        panel.getBtnDozvoli().setVisible(false);
        postaviTipove();
    }
    
        private void postaviTipove() {
         try {
            List<Model> listaModela = Komunikacija.getInstance().VratiSveModele();
            for (Model model : listaModela) {
                panel.getCmbModelAutomobila().addItem(model);
            }
        } catch (IOException ex) {
            Logger.getLogger(KontrolerUnosAutomobila.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sacuvaj() {
try {
            
             Automobil a = kreiraj();
             if(a == null){
              JOptionPane.showMessageDialog(panel, "Pogrešno ste uneli neku vrednost.", "Greška", JOptionPane.ERROR_MESSAGE);
             return;
            }
             boolean sig = Komunikacija.getInstance().ZapamtiAutomobil(a);
             if(sig){
                 JOptionPane.showMessageDialog(panel, "Sistem je zapamtio automobil!", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                 panel.getTxtCena().setText("");
                 panel.getTxtNaziv().setText("");
                 panel.getTxtOpis().setText("");
                 panel.getTxtVlasnik().setText("");
                 
             }else{
                 JOptionPane.showMessageDialog(panel, "Sistem ne može da zapamti automobil.", "Greška", JOptionPane.ERROR_MESSAGE);
             }
         } catch (IOException ex) {
             Logger.getLogger(KontrolerUnosAutomobila.class.getName()).log(Level.SEVERE, null, ex);
         }    
    }

    private void izmeni() {
try {
             Automobil a = kreiraj();
             if(a == null) return; 
             a.setAutomobilID(izmena.getAutomobilID());
             boolean sig = Komunikacija.getInstance().IzmeniAutomobil(a);
             if(sig){
                 JOptionPane.showMessageDialog(panel, "Sistem je izvršio izmenu!", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
             }else{
                 JOptionPane.showMessageDialog(panel, "Sistem ne može da izvrši izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
             }
         } catch (IOException ex) {
             Logger.getLogger(KontrolerUnosAutomobila.class.getName()).log(Level.SEVERE, null, ex);
         }    
    }


    private void dozvoliIzmenu() {
        postaviTrue();    
    }

    private Automobil kreiraj() {
        try{
            String naziv = panel.getTxtNaziv().getText();
            String vlasnik = panel.getTxtVlasnik().getText();
            String opis = panel.getTxtOpis().getText();
            if(naziv.equals("") || vlasnik.equals("") || opis.equals("") ){
                JOptionPane.showMessageDialog(panel, "Polja moraju biti popunjena!", "Greška", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            
            double cena = Double.parseDouble(panel.getTxtCena().getText());
            if(cena <=0){
                JOptionPane.showMessageDialog(panel, "Cena mora biti veća od 0", "Greška", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            Model tip = (Model) panel.getCmbModelAutomobila().getSelectedItem();
            Automobil p = new Automobil(new Long(0), naziv, vlasnik, opis, cena, tip);
            return p;
        }catch(Exception e){
            JOptionPane.showMessageDialog(panel, "Pogrešan unos vrednosti!", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void postaviTrue() {
        panel.getBtnDozvoli().setEnabled(false);
        panel.getBtnIzmeni().setEnabled(true);
        panel.getTxtNaziv().setEnabled(true);
        panel.getTxtVlasnik().setEnabled(true);
        panel.getTxtOpis().setEnabled(true);
        panel.getTxtCena().setEnabled(true);
        panel.getCmbModelAutomobila().setEnabled(true);
            
    }

    
     private void postaviFalse() {
        panel.getBtnDozvoli().setVisible(true);
        panel.getBtnIzmeni().setVisible(true);
        panel.getBtnIzmeni().setEnabled(false);
        panel.getBtnSacuvaj().setEnabled(false);
        panel.getBtnSacuvaj().setVisible(false);
        panel.getTxtNaziv().setEnabled(false);
        panel.getTxtVlasnik().setEnabled(false);
        panel.getTxtOpis().setEnabled(false);
        panel.getTxtCena().setEnabled(false);
        panel.getCmbModelAutomobila().setEnabled(false);    
     }

    private void postaviVrednosti() {
        panel.getTxtNaziv().setText(izmena.getNaziv());
        panel.getTxtVlasnik().setText(izmena.getVlasnik());
        panel.getTxtOpis().setText(izmena.getOpis());
        panel.getTxtCena().setText(String.valueOf(izmena.getCenaPoDanu()));
        panel.getCmbModelAutomobila().setSelectedItem(izmena.getTipAutomobilaID());    
    }
}
