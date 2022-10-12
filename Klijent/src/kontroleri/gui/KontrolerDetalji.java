/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

import domen.Iznajmljivanje;
import domen.Klijent;
import gui.PanelKlijentDetalji;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modeli.tabele.IznajmljivanjeModelTabele;
import servis.Komunikacija;

/**
 *
 * @author Lenovo
 */
class KontrolerDetalji {

    private static KontrolerDetalji instanca;
    private PanelKlijentDetalji panel;
    private List<Iznajmljivanje> lista;
     Klijent klijent;
 
    private final List<Iznajmljivanje> nova = new ArrayList<>();
    
    
    static KontrolerDetalji getInstance() {
        if(instanca == null){
            instanca = new KontrolerDetalji();
        }
        return instanca;
    }

    public KontrolerDetalji() {
    }
    
    public PanelKlijentDetalji getPanel(Klijent k) {
        initializePanel();
        this.klijent = k;
        priprema();
        panel.setVisible(true);
        return panel;
    }
    
   

    private void initializePanel() {
        panel = new PanelKlijentDetalji();
        
    }

    private void priprema() {

        postaviIznajmljivanja();
        ubaciUListu();
        izbaciIzListe();
        postaviTabelu();
        System.out.println(klijent.getIme());
    }

    private void postaviTabelu() {
           panel.getTblIznajmljivanja().setModel(new IznajmljivanjeModelTabele(nova));
           
    }
  private void ubaciUListu(){
        for (Iznajmljivanje izna : lista) {
            if(izna.getKlijent().getKlijentId() == klijent.getKlijentId()){
                nova.add(izna);
                return;
               
            }
          
        }
    } 

    private void postaviIznajmljivanja() {
        try {
            lista = Komunikacija.getInstance().VratiSvaIznajmljivanja();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerPretragaIznajmljivanja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    private void izbaciIzListe() {
            for (Iznajmljivanje iznajmljivanje : nova) {
            if(iznajmljivanje.getKlijent().getKlijentId() != klijent.getKlijentId()){
                nova.remove(iznajmljivanje);
            }
        }
    }

   
   

}