/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

import domen.Iznajmljivanje;
import domen.Klijent;
import gui.PanelUnosKlijenta;
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
class KontrolerUnosKlijenta {

    private static KontrolerUnosKlijenta instanca;
    private PanelUnosKlijenta panel;
    private List<Iznajmljivanje> lista = new ArrayList();

    private KontrolerUnosKlijenta() {
    }

    public static KontrolerUnosKlijenta getInstance() {
if (instanca == null) {
            instanca = new KontrolerUnosKlijenta();
        }
        return instanca;    
    }
    
    
    public PanelUnosKlijenta getPanel() {
       
        initializePanel();
        panel.setVisible(true);
        return panel;
    }

    private void initializePanel() {
       panel = new PanelUnosKlijenta();
       //JOptionPane.showMessageDialog(panel, "Sistem je uspešno kreirao klijenta!", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
        addEventHandlers();
       
    } 
    private void addEventHandlers() {
        this.panel.getBtnSacuvaj().addActionListener(e -> sacuvaj());

    }

    private void sacuvaj() {
         try {
             
             Klijent k = kreiraj();
             
             boolean sig = Komunikacija.getInstance().ZapamtiKlijenta(k);
             
             if(sig){
                 JOptionPane.showMessageDialog(panel, "Sistem je zapamtio klijenta!", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                 panel.getTxtJMBG().setText("");
                 panel.getTxtIme().setText("");
                 panel.getTxtDrzava().setText("");
             }else{
                 JOptionPane.showMessageDialog(panel, "Sistem ne može da zapamti klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
             }
         } catch (IOException ex) {
             Logger.getLogger(KontrolerUnosKlijenta.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    private Klijent kreiraj() {
        try{
            
            String ime = panel.getTxtIme().getText();
            String jmbg = panel.getTxtJMBG().getText();
            String drzava = panel.getTxtDrzava().getText();
            if(ime.equals("") || drzava.equals("")){
                JOptionPane.showMessageDialog(panel, "Niste uneli vrednosti za sva polja!", "Greška", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if(jmbg.length() != 13){
                JOptionPane.showMessageDialog(panel, "JMBG mora imati 13 cifara", "Greška", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            Klijent k = new Klijent(new Long(0), jmbg, ime, drzava);
            
            return k;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(panel, "Pogrešan unos vrednosti!", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
}
}
