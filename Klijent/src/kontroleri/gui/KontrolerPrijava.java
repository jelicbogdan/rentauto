/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

import domen.Klijent;
import domen.Korisnik;
import gui.PanelPrijava;
import java.awt.Window;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import servis.Komunikacija;
import sesija.Sesija;

/**
 *
 * @author Lenovo
 */
class KontrolerPrijava {

     private static KontrolerPrijava instanca;
    private PanelPrijava panel;
    private List<Klijent> lista;

    private KontrolerPrijava() {
    }

    static KontrolerPrijava getInstance() {
 if (instanca == null) {
            instanca = new KontrolerPrijava();
        }
        return instanca;    
    }
    
     public PanelPrijava getPanel() {
       
        initializePanel();
        panel.setVisible(true);
        return panel;
    }

    private void initializePanel() {
       panel = new PanelPrijava();
       addEventHandlers();
       
    } 
    private void addEventHandlers() {
        this.panel.getBtnPrijavi().addActionListener(e -> prijavi());
    }

    private void prijavi() {
        Korisnik k = kreiraj();
        if(k == null) return;
         try {
             Korisnik kor = Komunikacija.getInstance().PrijaviKorisnika(k);
             if(kor == null){
                 JOptionPane.showMessageDialog(panel, "Sistem ne može da prijavi korisnika!", "Greška", JOptionPane.ERROR_MESSAGE);
                 return;
             }
             JOptionPane.showMessageDialog(panel, "Sistem je uspešno prijavio korisnika!", "Prijava", JOptionPane.INFORMATION_MESSAGE);
             Sesija.getInstance().setKorisnik(kor);
             zatvori();
             KoordinatorUI.getInstance().frmGlavna();

         } catch (IOException ex) {
             Logger.getLogger(KontrolerPrijava.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
   
    private void zatvori() {
        Window w = SwingUtilities.getWindowAncestor(this.panel);
        w.dispose();
    }

    private Korisnik kreiraj() {
        String kor = panel.getTxtUsername().getText();
        String loz = panel.getTxtPassword().getText();
        Korisnik k = new Korisnik();
        k.setKorisnickoIme(kor);
        k.setKorisnickaLozinka(loz);
        return k;
    }
    
}

