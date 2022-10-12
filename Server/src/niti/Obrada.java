/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Automobil;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Korisnik;
import domen.Model;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Kontroler;
import obrada.KriterijumPretrage;
import obrada.Odgovor;
import obrada.Operacije;
import obrada.Zahtev;

/**
 *
 * @author Lenovo
 */
public class Obrada extends Thread {
 
    
    private final Socket socket;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;
    private Korisnik korisnikLogin;

    public Obrada(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new ObjectInputStream(socket.getInputStream());
        this.output = new ObjectOutputStream(socket.getOutputStream());
    }

    public Socket vratiSoket() {
        return socket;
    }

    @Override
    public void run() {
        while (socket.isClosed() == false) {
            try {
                Zahtev zahtev = (Zahtev) input.readObject();
                Odgovor odgovor = ObradiZahtev(zahtev);
                vratiPoruku(odgovor);
            } catch (EOFException ex) {
                try {
                    socket.close();
                    storage.Storage.getInstance().getKorisnici().remove(korisnikLogin);
                    
                    break;
                } catch (IOException ex1) {
                    Logger.getLogger(Obrada.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException | ClassNotFoundException ex) {
                try {
                    storage.Storage.getInstance().getKorisnici().remove(korisnikLogin);
                    socket.close();
                } catch (IOException ex1) {
                    Logger.getLogger(Obrada.class.getName()).log(Level.SEVERE, null, ex1);
                }
                System.out.println("Otisao: " + korisnikLogin.getKorisnickoIme());
            }
        }
    }
    private void vratiPoruku(Odgovor odgovor) throws IOException {
        this.output.writeObject(odgovor);
    }

    private Odgovor ObradiZahtev(Zahtev zahtev) {
        switch(zahtev.getOperacija()){
            case Operacije.PrijaviKorisnika  :return PrijaviKorisnika((Korisnik) zahtev.getObjekat());
            case Operacije.ZapamtiKlijenta   :return ZapamtiKlijenta((Klijent) zahtev.getObjekat());
            case Operacije.ZapamtiAutomobil   :return ZapamtiAutomobil((Automobil) zahtev.getObjekat());
            case Operacije.ZapamtiIznajmljivanje   :return ZapamtiIznajmljivanje((List<Iznajmljivanje>) zahtev.getObjekat());
            case Operacije.PrikaziKlijente    :return PrikaziKlijente((KriterijumPretrage) zahtev.getObjekat());
            case Operacije.PrikaziIznajmljivanja   :return PrikaziIznajmljivanja((KriterijumPretrage) zahtev.getObjekat());
            case Operacije.PronadjiAutomobil   :return PronadjiAutomobil((KriterijumPretrage) zahtev.getObjekat());
            case Operacije.UcitajAutomobil     :return UcitajAutomobil((Automobil) zahtev.getObjekat());
            case Operacije.UcitajKlijenta   :return UcitajKlijenta((Klijent) zahtev.getObjekat());
            case Operacije.UcitajIznajmljivanje   :return UcitajIznajmljivanje((Iznajmljivanje) zahtev.getObjekat());
            case Operacije.VratiSveModele   :return VratiSveModele();
            case Operacije.VratiSveAutomobile   :return VratiSveAutomobile();
            case Operacije.VratiSvaIznajmljivanja    :return VratiSvaIznajmljivanja();
            case Operacije.VratiSveKlijente    :return VratiSveKlijente();
            case Operacije.IzmeniAutomobil    :return IzmeniAutomobil((Automobil) zahtev.getObjekat());
            case Operacije.IzmeniIznajmljivanje    :return IzmeniIznajmljivanje((Iznajmljivanje) zahtev.getObjekat());
            case Operacije.ObrisiIznajmljivanje :return ObrisiIznajmljivanje((Iznajmljivanje) zahtev.getObjekat());
        }
        return null;
    }

    private Odgovor PrijaviKorisnika(Korisnik korisnik) {
        
         Odgovor odgovor; 
        Korisnik korisnikPrijava = Kontroler.getInstance().PrijaviKorisnika(korisnik);
        if(korisnikPrijava != null){
            odgovor = new Odgovor(true, korisnikPrijava);
            storage.Storage.getInstance().getKorisnici().add(korisnikPrijava);
            this.korisnikLogin = korisnikPrijava;
        }else{
            odgovor = new Odgovor(false, korisnikPrijava);
        }
        return odgovor;
    }

    private Odgovor ZapamtiKlijenta(Klijent klijent) {
        Odgovor odgovor; 
        boolean signal = Kontroler.getInstance().ZapamtiKlijenta(klijent);
        odgovor = new Odgovor(signal, "");
        return odgovor;
    }

    private Odgovor ZapamtiAutomobil(Automobil a) {
            Odgovor odgovor; 
        boolean signal = Kontroler.getInstance().ZapamtiAutomobil(a);
        odgovor = new Odgovor(signal, "");
        return odgovor;
    }

    private Odgovor ZapamtiIznajmljivanje(List<Iznajmljivanje> iznajmljivanje) {
         Odgovor odgovor; 
        boolean signal = Kontroler.getInstance().ZapamtiIznajmljivanje(iznajmljivanje);
        odgovor = new Odgovor(signal, "");
        return odgovor;
    }

    private Odgovor PrikaziKlijente(KriterijumPretrage kriterijumPretrage) {
        Odgovor odgovor; 
        List<Klijent> lista = Kontroler.getInstance().PrikaziKlijente(kriterijumPretrage);
        if(lista != null){
            odgovor = new Odgovor(true, lista);
        }else{
            odgovor = new Odgovor(false, lista);
        }
        return odgovor;
    }

    private Odgovor PrikaziIznajmljivanja(KriterijumPretrage kriterijumPretrage) {
        Odgovor odgovor; 
        List<Iznajmljivanje> lista = Kontroler.getInstance().PrikaziIznajmljivanja(kriterijumPretrage);
        if(lista != null){
            odgovor = new Odgovor(true, lista);
        }else{
            odgovor = new Odgovor(false, lista);
        }
        return odgovor;
    }

    private Odgovor PronadjiAutomobil(KriterijumPretrage kriterijumPretrage) {
            Odgovor odgovor; 
        List<Automobil> lista = Kontroler.getInstance().PronadjiAutomobil(kriterijumPretrage);
        if(lista != null){
            odgovor = new Odgovor(true, lista);
        }else{
            odgovor = new Odgovor(false, lista);
        }
        return odgovor;    }

    private Odgovor UcitajAutomobil(Automobil a) {
         Odgovor odgovor; 
        Automobil element = Kontroler.getInstance().UcitajAutomobil(a);
        if(element != null){
            odgovor = new Odgovor(true, element);
        }else{
            odgovor = new Odgovor(false, element);
        }
        return odgovor;
    }

    private Odgovor UcitajKlijenta(Klijent klijent) {
             Odgovor odgovor; 
        Klijent lista = Kontroler.getInstance().UcitajKlijenta(klijent);
        if(lista != null){
            odgovor = new Odgovor(true, lista);
        }else{
            odgovor = new Odgovor(false, lista);
        }
        return odgovor;
    }

    private Odgovor UcitajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
             Odgovor odgovor; 
        Iznajmljivanje lista = Kontroler.getInstance().UcitajIznajmljivanje(iznajmljivanje);
        if(lista != null){
            odgovor = new Odgovor(true, lista);
        }else{
            odgovor = new Odgovor(false, lista);
        }
        return odgovor;
    }

    private Odgovor VratiSveModele() {
             Odgovor odgovor; 
        List<Model> lista = Kontroler.getInstance().VratiSveModele();
        if(lista != null){
            odgovor = new Odgovor(true, lista);
        }else{
            odgovor = new Odgovor(false, lista);
        }
        return odgovor;
    }

    private Odgovor VratiSveAutomobile() {
            Odgovor odgovor; 
        List<Automobil> lista = Kontroler.getInstance().VratiSveAutomobile();
        if(lista != null){
            odgovor = new Odgovor(true, lista);
        }else{
            odgovor = new Odgovor(false, lista);
        }
        return odgovor;
    }

    private Odgovor VratiSvaIznajmljivanja() {
             Odgovor odgovor; 
        List<Iznajmljivanje> lista = Kontroler.getInstance().VratiSvaIznajmljivanja();
        if(lista != null){
            odgovor = new Odgovor(true, lista);
        }else{
            odgovor = new Odgovor(false, lista);
        }
        return odgovor;
    }

    private Odgovor VratiSveKlijente() {
             Odgovor odgovor; 
        List<Klijent> lista = Kontroler.getInstance().VratiSveKlijente();
        if(lista != null){
            odgovor = new Odgovor(true, lista);
        }else{
            odgovor = new Odgovor(false, lista);
        }
        return odgovor;
    }

    private Odgovor IzmeniAutomobil(Automobil a) {
            Odgovor odgovor; 
        boolean signal = Kontroler.getInstance().IzmeniAutomobil(a);
        odgovor = new Odgovor(signal, "");
        return odgovor;
    }

    private Odgovor IzmeniIznajmljivanje(Iznajmljivanje iznajmljivanje) {
            Odgovor odgovor; 
        boolean signal = Kontroler.getInstance().IzmeniIznajmljivanje(iznajmljivanje);
        odgovor = new Odgovor(signal, "");
        return odgovor;
    }

    private Odgovor ObrisiIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        Odgovor odgovor;
        boolean signal = Kontroler.getInstance().ObrisiIznajmljivanje(iznajmljivanje);
        odgovor = new Odgovor(signal, "");
        return odgovor;
                
    }
}
