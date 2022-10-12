/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo
 */
public class UcitajKlijentaSO extends OpstaSistemskaOperacija{
    
    public UcitajKlijentaSO(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }
    @Override
    protected void operation() throws Exception {
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Klijent());
        List<Klijent> lista = dblista.stream().map(Klijent.class::cast).collect(Collectors.toList());
        Klijent k = (Klijent) odo;
        for (Klijent el : lista) {
            if(el.getKlijentId().equals(k.getKlijentId())){
                odo = el;
                return;
            }
        }
        throw new Exception();
    }
    
}