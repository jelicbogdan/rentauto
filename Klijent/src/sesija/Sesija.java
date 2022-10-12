/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesija;

import domen.Korisnik;

/**
 *
 * @author Lenovo
 */
public class Sesija {
    
    
     private static Sesija instance;
    private Korisnik korisnik;

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik loginKorisnik) {
        this.korisnik = loginKorisnik;
    }

    private Sesija() {
      
    }
    public static Sesija getInstance() {
        if (instance == null) {
            instance = new Sesija();
        }
        return instance;
    }
}
