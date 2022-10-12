/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler.gui;

import gui.PanelKonfiguracija;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
class KonfiguracijaKontroler {
    
    private static KonfiguracijaKontroler instance;
    private PanelKonfiguracija panel;
    
    static KonfiguracijaKontroler getInstance() {
            if(instance == null) { 
                instance = new KonfiguracijaKontroler();
            }
            return instance;
    }

    public PanelKonfiguracija getPanel() {
        this.panel = new PanelKonfiguracija();
        priprema();
        pripremaServer();
        addEventListeners();
        return panel;
        
    }

    

    private void addEventListeners() {
        this.panel.getBtnIzmeniBaza().addActionListener(e -> omoguciIzmenuBaza());
        this.panel.getBtnSacuvajBaza().addActionListener(e -> {
            try {
                sacuvajBaza();
            } catch (Exception ex) {
                Logger.getLogger(KonfiguracijaKontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.panel.getBtnIzmeniPort().addActionListener(e -> omoguciIzmenuServer());
        this.panel.getBtnSacuvajPort().addActionListener(e -> sacuvajServer());
    }

    private void omoguciIzmenuBaza() {
        panel.getBtnIzmeniBaza().setEnabled(false);
        panel.getBtnSacuvajBaza().setEnabled(true);
        panel.getTxtDrajver().setEnabled(true);
        panel.getTxtPassword().setEnabled(true);
        panel.getTxtUsername().setEnabled(true);
        panel.getTxtUrl().setEnabled(true);
    }

    private void sacuvajBaza() {
             try {
            boolean signal = sacuvaj();
            if (signal) {
                setFalse();
                JOptionPane.showMessageDialog(panel, "Uspešna izmena podataka za konfiguraciju baze", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                priprema();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(panel, "Došlo je do greske prilikom izmene podataka", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void priprema() {
            setValues();
            setFalse();
    }
    private void setValues() {
        FileInputStream fileInputStream = null;
        try {
            //ovo je za pisanje
            Properties properties = new Properties();
            String propertiesFileName = "konfiguracija/db.properties";
            fileInputStream = new FileInputStream(propertiesFileName);
            properties.load(fileInputStream);
            this.panel.getTxtUrl().setText(properties.getProperty("url"));
            this.panel.getTxtDrajver().setText(properties.getProperty("driver"));
            this.panel.getTxtUsername().setText(properties.getProperty("user"));
            this.panel.getTxtPassword().setText(properties.getProperty("password"));
            
            fileInputStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Došlo je do greške prilikom učitavanja konfiguracije iz fajla", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
 private boolean sacuvaj() throws FileNotFoundException,IOException {
        
        if(panel.getTxtDrajver().getText() == "" || panel.getTxtUsername().getText() == "" || panel.getTxtUrl().getText() == "" || panel.getTxtPassword().getText() == ""){
            JOptionPane.showMessageDialog(panel,"Prazan unos", "Error", JOptionPane.ERROR_MESSAGE);
        }
       
        Properties props = new Properties();
        props.put("driver", this.panel.getTxtDrajver().getText().trim());
        props.put("url", this.panel.getTxtUrl().getText().trim());
        props.put("user", this.panel.getTxtUsername().getText().trim());
        props.put("password", this.panel.getTxtPassword().getText().trim());
        String path = "konfiguracija/db.properties";
        FileOutputStream outputStrem = new FileOutputStream(path);
        props.store(outputStrem, "Ovo su parametri za komunikaciju sa bazom podataka");
        return true;
    }
    
  private void setFalse() {
        panel.getBtnIzmeniBaza().setEnabled(true);
        panel.getTxtDrajver().setEnabled(false);
        panel.getTxtPassword().setEnabled(false);
        panel.getTxtUsername().setEnabled(false);
        panel.getTxtUrl().setEnabled(false);
        panel.getBtnSacuvajBaza().setEnabled(false);
    }
        
    private void omoguciIzmenuServer() {
         panel.getBtnIzmeniPort().setEnabled(false);
        panel.getBtnSacuvajPort().setEnabled(true);
        panel.getTxtPort().setEnabled(true);
    }

    private void sacuvajServer() {
        try {
            int port = vratiPort();
            if(port == -1 )return;
            sacuvajPort(port);
            JOptionPane.showMessageDialog(panel, "Uspešno je sačuvan port za konfiguraciju servera!", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            pripremaServer();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Došlo je do greške", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private int vratiPort() throws Exception {
        try {
            if(panel.getTxtPort().getText().equals("")){
                JOptionPane.showMessageDialog(panel, "Morate uneti vrednosti porta", "Greška", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
               
            int port = Integer.parseInt(panel.getTxtPort().getText());
            if (port < 0 || port > 65535) {
                JOptionPane.showMessageDialog(panel, "Broj porta mora biti izmedju 0 i 65535", "Greška", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
            return port;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel, "Broj porta mora biti cifra", "Greška", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private void pripremaServer() {
        vratiPodatkeForma();
        panel.getBtnIzmeniPort().setEnabled(true);
        panel.getBtnSacuvajPort().setEnabled(false);
        panel.getTxtPort().setEnabled(false);
    }

    private void vratiPodatkeForma() {
        FileInputStream fileInputStream = null;
        try {
            Properties properties = new Properties();
            String propertiesFileName = "konfiguracija/server.properties";
            fileInputStream = new FileInputStream(propertiesFileName);
            properties.load(fileInputStream);
            this.panel.getTxtPort().setText(properties.getProperty("socket_port"));
            fileInputStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(panel, ex.getMessage(), "Greška pri učitavanju parametara", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sacuvajPort(int port) throws FileNotFoundException, IOException {
      Properties props = new Properties();
      props.put("socket_port", port+"");
      String path = "konfiguracija/server.properties";
      FileOutputStream outputStrem = new FileOutputStream(path);
      props.store(outputStrem, "Broj porta na kome se pokreće server");
    }
    
    
    }

   

   

    
    


