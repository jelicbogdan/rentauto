/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Model;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo
 */
public class VratiSveModeleSO extends OpstaSistemskaOperacija {
    
      List<Model> lista = new ArrayList();
    public VratiSveModeleSO(OpstiDomenskiObjekat odo,List<Model> lista) {
        this.odo = odo;
        this.lista = lista;
    }
    @Override
    protected void operation() throws Exception {
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Model());
        List<Model> lista2 = dblista.stream().map(Model.class::cast).collect(Collectors.toList());
        for (Model el : lista2) {
            lista.add(el);
        }
    }
}
