/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

import domen.Klijent;
import gui.PanelPretragaKlijenta;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.tabele.KlijentModelTabele;
import obrada.KriterijumPretrage;
import servis.Komunikacija;

/**
 *
 * @author Lenovo
 */
class KontrolerPretragaKlijenta {

     private static KontrolerPretragaKlijenta instanca;
    private PanelPretragaKlijenta panel;
    private List<Klijent> lista;

    private KontrolerPretragaKlijenta() {
    }
    
   public static KontrolerPretragaKlijenta getInstance() {
            if (instanca == null) {
            instanca = new KontrolerPretragaKlijenta();
        }
        return instanca;   
   }
    public PanelPretragaKlijenta getPanel() {
       
        initializePanel();
        priprema();
        panel.setVisible(true);
        return panel;
    }

    private void initializePanel() {
       panel = new PanelPretragaKlijenta();
       addEventHandlers();
       
    } 
    private void addEventHandlers() {
        this.panel.getBtnPretraga().addActionListener(e -> pretraga());
        this.panel.getBtnResetuj().addActionListener(e -> priprema());
        this.panel.getBtnDetalji().addActionListener(e -> detalji());
    }

    private void priprema() {
        postaviKlijente();
        postaviTabelu();
       
    }

    private void postaviTabelu() {
       panel.getTblPretraga().setModel(new KlijentModelTabele(lista));
    }

    private void postaviKlijente() {
        try {
            lista = Komunikacija.getInstance().VratiSveKlijente();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerPretragaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pretraga() {
         try {
            if(panel.getTxtPretraga().getText().equals("")){
                JOptionPane.showMessageDialog(panel, "Morate uneti tekst pretrage!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            List<Klijent> listaPre = Komunikacija.getInstance().PrikaziKlijente(new KriterijumPretrage(panel.getTxtPretraga().getText()));
            if(listaPre == null || listaPre.size() ==0){
                JOptionPane.showMessageDialog(panel, "Ne postoje klijenti za zadati kriterijum.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(panel, "Sistem je pronašao klijente za zadati kriterijum.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            lista = listaPre;
            postaviTabelu();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerPretragaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void detalji() {
                int row = panel.getTblPretraga().getSelectedRow();
                if(row == - 1){
                    JOptionPane.showMessageDialog(panel, "Niste izabrali klijenta.", "Greška",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Klijent k = lista.get(row);
                if( k == null) { 
            JOptionPane.showMessageDialog(panel, "Sistem ne može da učita klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }else{ JOptionPane.showMessageDialog(panel, "Sistem je učitao klijenta.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            KontrolerGlavnaForma kontroler = new KontrolerGlavnaForma();
            kontroler.frmDetaljiKlijent(k);
             priprema();
     
    }

    
    } 
}
