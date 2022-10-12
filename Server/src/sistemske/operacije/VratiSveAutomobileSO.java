/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Automobil;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo
 */
public class VratiSveAutomobileSO extends OpstaSistemskaOperacija {
 
    List<Automobil> lista = new ArrayList();
    public VratiSveAutomobileSO(OpstiDomenskiObjekat odo,List<Automobil> lista) {
        this.odo = odo;
        this.lista = lista;
    }
    @Override
    protected void operation() throws Exception {
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Automobil());
        List<Automobil> lista2 = dblista.stream().map(Automobil.class::cast).collect(Collectors.toList());
        for (Automobil el : lista2) {
            lista.add(el);
        }
    }
}
