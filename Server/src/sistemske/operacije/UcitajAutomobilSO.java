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
public class UcitajAutomobilSO extends OpstaSistemskaOperacija {
    
    
    public UcitajAutomobilSO(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }
    @Override
    protected void operation() throws Exception {
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Automobil());
        List<Automobil> lista = dblista.stream().map(Automobil.class::cast).collect(Collectors.toList());
        Automobil a = (Automobil) odo;
        for (Automobil el : lista) {
            if(a.getAutomobilID().equals(el.getAutomobilID())){
                odo = el; 
                return;
            }
        }
        throw new Exception();
    }
}
