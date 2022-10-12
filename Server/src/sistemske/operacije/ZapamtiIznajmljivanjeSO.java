/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Iznajmljivanje;
import domen.OpstiDomenskiObjekat;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ZapamtiIznajmljivanjeSO extends OpstaSistemskaOperacija {
    
    List<Iznajmljivanje> lista;
    public ZapamtiIznajmljivanjeSO(OpstiDomenskiObjekat odo,List<Iznajmljivanje> lista) {
        this.odo = odo;
        this.lista = lista;
    }
    @Override
    protected void operation() throws Exception {
        for (Iznajmljivanje iznajmljivanje : lista) {
            broker.DodajDomenskiObjekat(iznajmljivanje);
        }
    }
    
}
