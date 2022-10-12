/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Automobil;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo
 */
public class ZapamtiAutomobilSO extends OpstaSistemskaOperacija {
    
    
     public ZapamtiAutomobilSO(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }
    @Override
    protected void operation() throws Exception {
        Automobil a = (Automobil) odo;
        broker.DodajDomenskiObjekat(a);
    }
    private void proveraPostoji(Automobil a) throws Exception{
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Automobil());
        List<Automobil> lista2 = dblista.stream().map(Automobil.class::cast).collect(Collectors.toList());
        for (Automobil el : lista2) {
            if(el.getNaziv().toLowerCase().equals(a.getNaziv().toLowerCase())) throw new Exception();
        }
    }
}
