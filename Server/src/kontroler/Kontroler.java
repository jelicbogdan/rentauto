/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Model;
import domen.Automobil;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Korisnik;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import obrada.KriterijumPretrage;
import sistemske.operacije.IzmeniAutomobilSO;
import sistemske.operacije.IzmeniIznajmljivanjeSO;
import sistemske.operacije.ObrisiIznajmljivanjeSO;
import sistemske.operacije.OpstaSistemskaOperacija;
import sistemske.operacije.PrijaviKorisnikaSO;
import sistemske.operacije.PrikaziIznajmljivanjaSO;
import sistemske.operacije.PrikaziKlijenteSO;
import sistemske.operacije.PronadjiAutomobilSO;
import sistemske.operacije.UcitajAutomobilSO;
import sistemske.operacije.UcitajIznajmljivanjeSO;
import sistemske.operacije.UcitajKlijentaSO;
import sistemske.operacije.VratiSvaIznajmljivanjaSO;
import sistemske.operacije.VratiSveAutomobileSO;
import sistemske.operacije.VratiSveKlijenteSO;
import sistemske.operacije.VratiSveModeleSO;
import sistemske.operacije.ZapamtiAutomobilSO;
import sistemske.operacije.ZapamtiIznajmljivanjeSO;
import sistemske.operacije.ZapamtiKlijentaSO;

/**
 *
 * @author Lenovo
 */
public class Kontroler {
    
    private static Kontroler instance; 
    private Kontroler() {
    
    }
    public static Kontroler getInstance(){
        if(instance == null){
            instance = new Kontroler();
        }
        return instance;
    }

    public Korisnik PrijaviKorisnika(Korisnik korisnik) {
        try {
            OpstaSistemskaOperacija o = new PrijaviKorisnikaSO(korisnik);
            o.execute();
            Korisnik k = (Korisnik) o.vratiOdo();
            return k;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean ZapamtiKlijenta(Klijent klijent) {
        try {
            OpstaSistemskaOperacija o = new ZapamtiKlijentaSO(klijent);
            o.execute();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean ZapamtiAutomobil(Automobil a) {
        try {
            OpstaSistemskaOperacija o = new ZapamtiAutomobilSO(a);
            o.execute();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ZapamtiIznajmljivanje(List<Iznajmljivanje> iznajmljivanje) {
        try {
            OpstaSistemskaOperacija o = new ZapamtiIznajmljivanjeSO(new Iznajmljivanje(),iznajmljivanje);
            o.execute();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Klijent> PrikaziKlijente(KriterijumPretrage kriterijumPretrage) {
        try {
            List<Klijent> lista = new ArrayList();
            OpstaSistemskaOperacija o = new PrikaziKlijenteSO(kriterijumPretrage, lista);
            o.execute();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Iznajmljivanje> PrikaziIznajmljivanja(KriterijumPretrage kriterijumPretrage) {
        try {
            List<Iznajmljivanje> lista = new ArrayList();
            OpstaSistemskaOperacija o = new PrikaziIznajmljivanjaSO(kriterijumPretrage, lista);
            o.execute();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Automobil> PronadjiAutomobil(KriterijumPretrage kriterijumPretrage) {
        try {
            List<Automobil> lista = new ArrayList();
            OpstaSistemskaOperacija o = new PronadjiAutomobilSO(kriterijumPretrage, lista);
            o.execute();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Automobil UcitajAutomobil(Automobil a) {
        try {
            OpstaSistemskaOperacija o = new UcitajAutomobilSO(a);
            o.execute();
            Automobil auto  = (Automobil) o.vratiOdo();
            return auto;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Klijent UcitajKlijenta(Klijent klijent) {
        try {
            OpstaSistemskaOperacija o = new UcitajKlijentaSO(klijent);
            o.execute();
            Klijent v = (Klijent) o.vratiOdo();
            return v;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Iznajmljivanje UcitajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        try {
            OpstaSistemskaOperacija o = new UcitajIznajmljivanjeSO(iznajmljivanje);
            o.execute();
            Iznajmljivanje v = (Iznajmljivanje) o.vratiOdo();
            return v;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Model> VratiSveModele() {
        try {
            List<Model> lista = new ArrayList();
            OpstaSistemskaOperacija o = new VratiSveModeleSO(new Model(), lista);
            o.execute();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Automobil> VratiSveAutomobile() {
        try {
            List<Automobil> lista = new ArrayList();
            OpstaSistemskaOperacija o = new VratiSveAutomobileSO(new Automobil(), lista);
            o.execute();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Iznajmljivanje> VratiSvaIznajmljivanja() {
        try {
            List<Iznajmljivanje> lista = new ArrayList();
            OpstaSistemskaOperacija o = new VratiSvaIznajmljivanjaSO(new Iznajmljivanje(), lista);
            o.execute();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Klijent> VratiSveKlijente() {
        try {
            List<Klijent> lista = new ArrayList();
            OpstaSistemskaOperacija o = new VratiSveKlijenteSO(new Klijent(), lista);
            o.execute();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean IzmeniAutomobil(Automobil a) {
        try {
            OpstaSistemskaOperacija o = new IzmeniAutomobilSO(a);
            o.execute();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean IzmeniIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        try {
            OpstaSistemskaOperacija o = new IzmeniIznajmljivanjeSO(iznajmljivanje);
            o.execute();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean ObrisiIznajmljivanje(Iznajmljivanje iznajmljivanje){
        try {
            OpstaSistemskaOperacija o = new ObrisiIznajmljivanjeSO(iznajmljivanje);
            o.execute();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}

