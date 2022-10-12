/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import obrada.KriterijumPretrage;

/**
 *
 * @author Lenovo
 */
public class PrikaziKlijenteSO extends  OpstaSistemskaOperacija {
    
     List<Klijent> lista = new ArrayList();
    public PrikaziKlijenteSO(OpstiDomenskiObjekat odo,List<Klijent> lista) {
        this.odo = odo;
        this.lista = lista;
    }
    @Override
    protected void operation() throws Exception {
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Klijent());
        List<Klijent> lista2 = dblista.stream().map(Klijent.class::cast).collect(Collectors.toList());
        KriterijumPretrage kp = (KriterijumPretrage) odo;
        String pr = kp.getTekst().toLowerCase();
        for (Klijent el : lista2) {
            if(el.getIme().toLowerCase().contains(pr) || el.getDrzava().toLowerCase().contains(pr)
                    || el.getJmbg().toLowerCase().contains(pr)){
                lista.add(el);
            }
        }
    }
}