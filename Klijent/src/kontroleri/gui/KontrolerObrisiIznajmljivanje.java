/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

import gui.PanelObrisiIznajmljivanje;
import java.awt.Window;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lenovo
 */
class KontrolerObrisiIznajmljivanje {
        
        private static KontrolerObrisiIznajmljivanje instanca;
        private PanelObrisiIznajmljivanje panel;
        private boolean signal;

    public boolean isSignal() {
        return signal;
    }

    public KontrolerObrisiIznajmljivanje() {
    }
    
        
    static KontrolerObrisiIznajmljivanje getInstance() {
            if(instanca == null) {
                instanca = new KontrolerObrisiIznajmljivanje();
            }
            return instanca;
    }

   public PanelObrisiIznajmljivanje getPanel(boolean b) {
        
        panel = new PanelObrisiIznajmljivanje();
        addEventHandlers();
        panel.setVisible(true);
        return panel;
   }

    private void addEventHandlers() {
        
            this.panel.getBtnDa().addActionListener(e -> eventDa());
            this.panel.getBtnNe().addActionListener(e -> eventNe());
    }

    private void eventDa() {
            
        signal = true;
        Window w = SwingUtilities.getWindowAncestor(this.panel);
        w.dispose();
    }

    private void eventNe() {
        
        signal = false;
        Window w = SwingUtilities.getWindowAncestor(this.panel);
        w.dispose();
    }
    
}
