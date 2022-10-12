/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domen.Korisnik;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Storage {
    
    private static Storage instance;
    private List<Korisnik> korisnici = new ArrayList();

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

   

    private Storage() {
      
    }
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
            
        }
        return instance;
    }
    
}
