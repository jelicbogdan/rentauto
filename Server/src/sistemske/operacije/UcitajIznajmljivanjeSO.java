/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Iznajmljivanje;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo
 */
public class UcitajIznajmljivanjeSO extends OpstaSistemskaOperacija {
    
    public UcitajIznajmljivanjeSO(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }
    @Override
    protected void operation() throws Exception {
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Iznajmljivanje());
        List<Iznajmljivanje> lista = dblista.stream().map(Iznajmljivanje.class::cast).collect(Collectors.toList());
        Iznajmljivanje i = (Iznajmljivanje)odo;
        for (Iznajmljivanje el : lista) {
            if(i.getIznajmljivanjeId().equals(el.getIznajmljivanjeId())){
                odo = el;
                return;
            }
        }
        throw new Exception();
    }
}
