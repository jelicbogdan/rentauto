/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

import domen.Iznajmljivanje;
import gui.PanelPretragaIznajmljivanja;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modeli.tabele.IznajmljivanjeModelTabele;
import obrada.KriterijumPretrage;
import servis.Komunikacija;

/**
 *
 * @author Lenovo
 */
class KontrolerPretragaIznajmljivanja {

    
    private static KontrolerPretragaIznajmljivanja instanca;
    private PanelPretragaIznajmljivanja panel;
    private List<Iznajmljivanje> lista;

    private KontrolerPretragaIznajmljivanja() {
    }

    public static KontrolerPretragaIznajmljivanja getInstance() {
         if (instanca == null) {
            instanca = new KontrolerPretragaIznajmljivanja();
        }
        return instanca;
    }
    
     public PanelPretragaIznajmljivanja getPanel() {
       
        initializePanel();
        priprema();
        panel.setVisible(true);
        return panel;
    }

    private void initializePanel() {
       panel = new PanelPretragaIznajmljivanja();
        addEventHandlers();
       
    } 

    
    private void addEventHandlers() {
        this.panel.getBtnDetalji().addActionListener(e -> detalji());
        this.panel.getBtnPretraga().addActionListener(e -> pretraga());
        this.panel.getBtnResetuj().addActionListener(e -> priprema());
        this.panel.getBtnObrisi().addActionListener(e -> obrisi());
    }

    private void priprema() {
          postaviIznajmljivanja();
        postaviTabelu();
    }

    private void postaviIznajmljivanja() {
         try {
            lista = Komunikacija.getInstance().VratiSvaIznajmljivanja();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerPretragaIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void postaviTabelu() {
        panel.getTblPretraga().setModel(new IznajmljivanjeModelTabele(lista));
    }
    
    
    
    private void detalji() {
         int row = panel.getTblPretraga().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(panel, "Niste izabrali iznajmljivanje.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
      
       
        Iznajmljivanje izn = lista.get(row);
        if(izn == null ){
        JOptionPane.showMessageDialog(panel, "Sistem ne može da pronađe iznajmljivanje!", "Greška", JOptionPane.ERROR_MESSAGE);
        return;
        }else{ 
            JOptionPane.showMessageDialog(panel, "Sistem je učitao iznajmljivanje!", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
        
        KontrolerGlavnaForma kontroler = new KontrolerGlavnaForma();
        kontroler.frmUnosIznajmljivanja(izn);
        priprema();
    }
    }

    private void pretraga() {
        
    try {
            if(panel.getTxtPretraga().getText().equals("")){
                JOptionPane.showMessageDialog(panel, "Morate uneti tekst pretrage.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            List<Iznajmljivanje> listaPre = Komunikacija.getInstance().PrikaziIznajmljivanja(new KriterijumPretrage(panel.getTxtPretraga().getText()));
            if(listaPre == null || listaPre.size() ==0){
                JOptionPane.showMessageDialog(panel, "Ne postoje iznajmljivanja za zadati kriterijum!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(panel, "Sistem je pronašao iznajmljivanje po zadatom kriterijumu!", "", JOptionPane.INFORMATION_MESSAGE);
            lista = listaPre;
            postaviTabelu();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerPretragaIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obrisi() {
        try {
            int redTabele = panel.getTblPretraga().getSelectedRow();
            if(redTabele == -1){
                JOptionPane.showMessageDialog(panel, "Morate selektovati iznajmljivanje!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Iznajmljivanje iz = lista.get(redTabele);
            JPanel panel = KontrolerObrisiIznajmljivanje.getInstance().getPanel(false);
            postaviDialogNaFrame(panel);
            boolean delete = KontrolerObrisiIznajmljivanje.getInstance().isSignal();
            if(delete == false) {
                return;
            }
            if(Komunikacija.getInstance().obrisiIznajmljivanje(iz) == true){
                JOptionPane.showMessageDialog(panel, "Sistem je uspešno obrisao iznajmljivanje!", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                lista.remove(iz);
                postaviTabelu();
                return;
            }
            JOptionPane.showMessageDialog(panel, "Sistem ne može da obriše iznajmljivanje!", "Greška", JOptionPane.ERROR_MESSAGE);   
        } catch (IOException ex) {
            Logger.getLogger(KontrolerPretragaIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                
                
                        
    }

    private void postaviDialogNaFrame(JPanel panel) {
        JDialog dialog = new JDialog(new JFrame(), true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}

