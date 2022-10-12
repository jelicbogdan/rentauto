/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri.gui;

import domen.Automobil;
import domen.Iznajmljivanje;
import domen.Klijent;
import gui.FrameGlavnaForma;
import gui.PanelKlijentDetalji;
import gui.PanelPretragaAutomobila;
import gui.PanelPretragaIznajmljivanja;
import gui.PanelPretragaKlijenta;
import gui.PanelPrijava;
import gui.PanelUnosAutomobila;
import gui.PanelUnosIznajmljivanja;
import gui.PanelUnosKlijenta;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Lenovo
 */
public class KontrolerGlavnaForma {
    
    
     private FrameGlavnaForma frame;

    public void prikaziGlavnu() {
        if (frame == null) {
            frame = new FrameGlavnaForma();
            setActionListeners();
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
        }
        frame.setVisible(true);
        this.frame.getContentPane().removeAll();

}

    private void setActionListeners() {
        
        this.frame.getjMenuIznajmljivanjePretraga().addActionListener(e -> frmPretragaIznajmljivanja());
        this.frame.getjMenuIznajmljivanje().addActionListener(e -> frmPretragaIznajmljivanja());
        this.frame.getjMenuKlijentPretraga().addActionListener(e -> frmPretragaKlijenta());
        this.frame.getjMenuAutomobilPretraga().addActionListener(e -> frmPretragaAutomobila());
        this.frame.getjMenuIznajmljivanjeUnos().addActionListener(e -> frmUnosIznajmljivanja(null));
        this.frame.getjMenuKlijentUnos().addActionListener(e -> frmUnosKlijenta());
        this.frame.getjMenuAutomobilUnos().addActionListener(e -> frmUnosAutomobila(null));
    }

    
    public void dialog(JPanel panel, String title) {
        JDialog dialog = new JDialog(new JFrame(), true);
        dialog.add(panel);
        dialog.setTitle(title);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        
    }
    private void frmPretragaIznajmljivanja() {
        
        PanelPretragaIznajmljivanja panel = KontrolerPretragaIznajmljivanja.getInstance().getPanel();
        dialog(panel,"Pretraga iznajmljivanja");
    }

    private void frmUnosKlijenta() {
        PanelUnosKlijenta panel = KontrolerUnosKlijenta.getInstance().getPanel();
        dialog(panel,"Unos klijenta");
    }

    private void frmPretragaKlijenta() {
    PanelPretragaKlijenta panel = KontrolerPretragaKlijenta.getInstance().getPanel();
        dialog(panel,"Pretraga klijenata");
    }

    private void frmPretragaAutomobila() {
        PanelPretragaAutomobila panel = KontrolerPretragaAutomobila.getInstance().getPanel();
        dialog(panel,"Pretraga automobila");
    }

   public void frmUnosIznajmljivanja(Iznajmljivanje i ) {
        PanelUnosIznajmljivanja panel = KontrolerUnosIznajmljivanja.getInstance().getPanel(i);
        dialog(panel,"Unos iznajmljivanja");
    }

    public void frmUnosAutomobila(Automobil a) {
        PanelUnosAutomobila panel = KontrolerUnosAutomobila.getInstance().getPanel(a);
        dialog(panel,"Unos automobila");
    }
    
    public void frmPrijava() {
        PanelPrijava panel = KontrolerPrijava.getInstance().getPanel();
        dialog(panel,"Prijava Korisnika na sistem");
    }

    void frmDetaljiKlijent(Klijent k) {
        PanelKlijentDetalji panel = KontrolerDetalji.getInstance().getPanel(k);
        dialog(panel,"Iznajmljivanja klijenta");
    }
}
