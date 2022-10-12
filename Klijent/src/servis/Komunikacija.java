/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servis;

import domen.Automobil;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Korisnik;
import domen.Model;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import obrada.KriterijumPretrage;
import obrada.Odgovor;
import obrada.Operacije;
import obrada.Zahtev;

/**
 *
 * @author Lenovo
 */
public class Komunikacija {
    
 private static Komunikacija instance;

    private final Socket socket;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    
    private String serverAdress;
    private int serverPort;

    private Komunikacija() throws IOException {
        priprema();
        socket = new Socket(serverAdress, serverPort);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }
    public static Komunikacija getInstance() throws IOException {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    private void priprema() {
        try {
            Properties properties = new Properties();
            String propertiesFileName = "konfiguracija/server.properties";
            FileInputStream fileInputStream = new FileInputStream(propertiesFileName);
            properties.load(fileInputStream);
            this.serverAdress = properties.getProperty("adress");
            this.serverPort = Integer.parseInt(properties.getProperty("port"));

            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean ZapamtiKlijenta(Klijent klijent) {
        try {
            Zahtev zahtev = new Zahtev(Operacije.ZapamtiKlijenta,klijent);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            return odgovor.getStatus();
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ZapamtiAutomobil(Automobil a) {
        try {
            Zahtev zahtev = new Zahtev(Operacije.ZapamtiAutomobil,a);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            return odgovor.getStatus();
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ZapamtiIznajmljivanje(List<Iznajmljivanje> l) {
        try {
            Zahtev zahtev = new Zahtev(Operacije.ZapamtiIznajmljivanje,l);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            return odgovor.getStatus();
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean IzmeniIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        try {
            Zahtev zahtev = new Zahtev(Operacije.IzmeniIznajmljivanje,iznajmljivanje);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            return odgovor.getStatus();
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean IzmeniAutomobil(Automobil a) {
        try {
            Zahtev zahtev = new Zahtev(Operacije.IzmeniAutomobil,a);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            return odgovor.getStatus();
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Automobil UcitajAutomobil(Automobil a) {
         try {
            Zahtev zahtev = new Zahtev(Operacije.UcitajAutomobil,a);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                Automobil lista =  (Automobil) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Klijent UcitajKlijenta(Klijent klijent) {
         try {
            Zahtev zahtev = new Zahtev(Operacije.UcitajKlijenta,klijent);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                Klijent lista = (Klijent) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Iznajmljivanje UcitajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
         try {
            Zahtev zahtev = new Zahtev(Operacije.UcitajIznajmljivanje,iznajmljivanje);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                Iznajmljivanje lista = (Iznajmljivanje) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Korisnik PrijaviKorisnika(Korisnik korisnik) {
         try {
            Zahtev zahtev = new Zahtev(Operacije.PrijaviKorisnika,korisnik);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                Korisnik lista = (Korisnik) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Klijent> PrikaziKlijente(KriterijumPretrage kriterijumPretrage) {
         try {
            Zahtev zahtev = new Zahtev(Operacije.PrikaziKlijente,kriterijumPretrage);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                List<Klijent> lista = (List<Klijent>) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Iznajmljivanje> PrikaziIznajmljivanja(KriterijumPretrage kriterijumPretrage) {
         try {
            Zahtev zahtev = new Zahtev(Operacije.PrikaziIznajmljivanja,kriterijumPretrage);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                List<Iznajmljivanje> lista = (List<Iznajmljivanje>) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Automobil> PronadjiAutomobil(KriterijumPretrage kriterijumPretrage) {
         try {
            Zahtev zahtev = new Zahtev(Operacije.PronadjiAutomobil,kriterijumPretrage);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                List<Automobil> lista =  (List<Automobil>) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Model> VratiSveModele() {
         try {
            Zahtev zahtev = new Zahtev(Operacije.VratiSveModele,null);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                List<Model> lista =  (List<Model>) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Automobil> VratiSveAutomobile() {
         try {
            Zahtev zahtev = new Zahtev(Operacije.VratiSveAutomobile,null);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                List<Automobil> lista =  (List<Automobil>) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Iznajmljivanje> VratiSvaIznajmljivanja() {
         try {
            Zahtev zahtev = new Zahtev(Operacije.VratiSvaIznajmljivanja,null);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                List<Iznajmljivanje> lista = (List<Iznajmljivanje>) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Klijent> VratiSveKlijente() {
        try {
            Zahtev zahtev = new Zahtev(Operacije.VratiSveKlijente,null);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            if(odgovor.getStatus()){
                List<Klijent> lista = (List<Klijent>) odgovor.getPodaci();
                return lista;
            }
            return null;
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean obrisiIznajmljivanje(Iznajmljivanje iz) {
        
            try {
            Zahtev zahtev = new Zahtev(Operacije.ObrisiIznajmljivanje,iz);
            output.writeObject(zahtev);
            Odgovor odgovor = (Odgovor) input.readObject();
            return odgovor.getStatus();
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}

