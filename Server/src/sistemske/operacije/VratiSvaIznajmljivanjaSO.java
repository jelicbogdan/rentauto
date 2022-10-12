/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Iznajmljivanje;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo
 */
public class VratiSvaIznajmljivanjaSO extends OpstaSistemskaOperacija {
    
      List<Iznajmljivanje> lista = new ArrayList();
    public VratiSvaIznajmljivanjaSO(OpstiDomenskiObjekat odo,List<Iznajmljivanje> lista) {
        this.odo = odo;
        this.lista = lista;
    }
    @Override
    protected void operation() throws Exception {
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Iznajmljivanje());
        List<Iznajmljivanje> lista2 = dblista.stream().map(Iznajmljivanje.class::cast).collect(Collectors.toList());
        for (Iznajmljivanje el : lista2) {
            lista.add(el);
        }
    }
    
}