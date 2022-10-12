/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo
 */
public class PrijaviKorisnikaSO extends  OpstaSistemskaOperacija {
    
    public PrijaviKorisnikaSO(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }
    @Override
    protected void operation() throws Exception {
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Korisnik());
        List<Korisnik> lista2 = dblista.stream().map(Korisnik.class::cast).collect(Collectors.toList());
        Korisnik korisnik = (Korisnik)odo;
        for (Korisnik el : lista2) {
            if(korisnik.getKorisnickoIme().equals(el.getKorisnickoIme()) && korisnik.getKorisnickaLozinka().equals(el.getKorisnickaLozinka()) ){
                odo = el;
                return;
            }
        }
        throw new Exception();
    }
    
}

