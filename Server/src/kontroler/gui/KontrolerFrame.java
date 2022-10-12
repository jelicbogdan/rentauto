/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler.gui;

import gui.FrameServer;
import gui.PanelKonfiguracija;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import niti.KomunikacijaServer;

/**
 *
 * @author Lenovo
 */
public class KontrolerFrame {
 
    
    private FrameServer frame;
    private static KontrolerFrame instance;

    private KontrolerFrame() {
    }

    public static KontrolerFrame getInstance() {
        if (instance == null) {
            instance = new KontrolerFrame();
        }
        return instance;
    }

     public FrameServer getPanel() {
        initializePanel();
        frame.setVisible(true);
        return frame;
    }
     private void initializePanel() {
       frame = new FrameServer();
        setActionListeners();
       
    } 
    private void setActionListeners() {
        this.frame.getJmenuServerPokreni().addActionListener(e -> start());
        this.frame.getJmenuServerZaustavi().addActionListener(e -> stop());
        this.frame.getJmenuKonfiguracijaIzmeni().addActionListener(e -> panelIzmeniKonfiguraciju() );
        

    }


    private void start() {
        try {
            KomunikacijaServer.getInstance().PokreniServer();
            JOptionPane.showMessageDialog(frame, "Uspešno pokrenut server.", "Start server", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Greška prilikom pokretanja.", "Start server", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    private void stop() {
        try {
            KomunikacijaServer.getInstance().ZaustaviServer();
            JOptionPane.showMessageDialog(frame, "Uspešno zaustavljen server.", "Stop server", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(frame, "Greška prilikom zaustavljanja.", "Stop server", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void dialog(JPanel panel) {
        JDialog dialog = new JDialog(new JFrame(), true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    

    private void panelIzmeniKonfiguraciju() {
        PanelKonfiguracija panel = KonfiguracijaKontroler.getInstance().getPanel();
        dialog(panel);
    }
    
    
}

