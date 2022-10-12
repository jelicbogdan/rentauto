/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Automobil;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Lenovo
 */
public class IzmeniAutomobilSO extends  OpstaSistemskaOperacija{
    
    public IzmeniAutomobilSO(OpstiDomenskiObjekat odo) {
        this.odo = odo;
    }
    @Override
    protected void operation() throws Exception {
        Automobil p = (Automobil) odo;
        broker.IzmeniDomenskiObjekat(p);
    }
}
