/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Iznajmljivanje;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Lenovo
 */
public class ObrisiIznajmljivanjeSO extends OpstaSistemskaOperacija {

    public ObrisiIznajmljivanjeSO(OpstiDomenskiObjekat odo){
        this.odo = odo;
    }
    

    @Override
    protected void operation() throws Exception {
            Iznajmljivanje iznajmljivanje = (Iznajmljivanje) odo;
            broker.ObrisiDomenskiObjekat(iznajmljivanje);
    }
    
    
    
}

