/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

import domen.Automobil;
import gui.PanelPretragaAutomobila;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.tabele.AutomobilModelTabele;
import obrada.KriterijumPretrage;
import servis.Komunikacija;

/**
 *
 * @author Lenovo
 */
class KontrolerPretragaAutomobila {

    
    private static KontrolerPretragaAutomobila instanca;
    private PanelPretragaAutomobila panel;
    private List<Automobil> lista;

    public KontrolerPretragaAutomobila() {
    }

    
    static KontrolerPretragaAutomobila getInstance() {
         if (instanca == null) {
            instanca = new KontrolerPretragaAutomobila();
        }
        return instanca;
    }
    
     public PanelPretragaAutomobila getPanel() {
       
        initializePanel();
        priprema();
        panel.setVisible(true);
        return panel;
    }

    private void initializePanel() {
       panel = new PanelPretragaAutomobila();
       addEventHandlers();
       
    } 

    private void addEventHandlers() {
        this.panel.getBtnDetalji().addActionListener(e -> detalji());
        this.panel.getBtnPretraga().addActionListener(e -> pretraga());
        this.panel.getBtnResetuj().addActionListener(e -> priprema());
    }

    private void priprema() {
        postaviAutomobile();
        postaviTabelu();
    }

    private void postaviTabelu() {
        panel.getTblPretraga().setModel(new AutomobilModelTabele(lista));
    }

    private void postaviAutomobile() {
 try {
            lista = Komunikacija.getInstance().VratiSveAutomobile();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerPretragaAutomobila.class.getName()).log(Level.SEVERE, null, ex);
        }    }

   
    
    private void detalji() {
        int row = panel.getTblPretraga().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(panel, "Niste izabrali iznajmljivanje.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Automobil a = lista.get(row);
        if( a == null) { 
            JOptionPane.showMessageDialog(panel, "Sistem ne može da učita automobil.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }else{ JOptionPane.showMessageDialog(panel, "Sistem je učitao automobil.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
        KontrolerGlavnaForma kontroler = new KontrolerGlavnaForma();
        kontroler.frmUnosAutomobila(a);
        priprema();
    }
     }   

    private void pretraga() {
try {
            if(panel.getTxtPretraga().getText().equals("")){
                JOptionPane.showMessageDialog(panel, "Morate uneti tekst pretrage!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            List<Automobil> listaPre = Komunikacija.getInstance().PronadjiAutomobil(new KriterijumPretrage(panel.getTxtPretraga().getText()));
            if(listaPre == null || listaPre.size() ==0){
                JOptionPane.showMessageDialog(panel, "Sistem ne može da pronađe automobil po zadatom kriterijumu!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(panel, "Sistem je pronašao automobil po zadatom kriterijumu!", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            lista = listaPre;
            postaviTabelu();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerPretragaKlijenta.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
