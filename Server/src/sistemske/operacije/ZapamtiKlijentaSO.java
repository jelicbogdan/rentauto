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
public class ZapamtiKlijentaSO extends OpstaSistemskaOperacija {
    
    public ZapamtiKlijentaSO(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }
    @Override
    protected void operation() throws Exception {
        Klijent klijent = (Klijent) odo;
        proveraPostoji(klijent);
        broker.DodajDomenskiObjekat(klijent);
    }
    
    private void proveraPostoji(Klijent klijent) throws Exception{
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Klijent());
        List<Klijent> lista2 = dblista.stream().map(Klijent.class::cast).collect(Collectors.toList());
        for (Klijent el : lista2) {
            if(el.getJmbg().equals(klijent.getJmbg())){
                throw new Exception();
            }
        }
    }
}
