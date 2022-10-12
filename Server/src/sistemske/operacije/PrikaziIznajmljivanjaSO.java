/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske.operacije;

import domen.Iznajmljivanje;
import domen.OpstiDomenskiObjekat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import obrada.KriterijumPretrage;

/**
 *
 * @author Lenovo
 */
public class PrikaziIznajmljivanjaSO extends OpstaSistemskaOperacija {
    
    List<Iznajmljivanje> lista = new ArrayList();
    public PrikaziIznajmljivanjaSO(OpstiDomenskiObjekat odo,List<Iznajmljivanje> lista) {
        this.odo = odo;
        this.lista = lista;
    }
    @Override
    protected void operation() throws Exception {
        List<OpstiDomenskiObjekat> dblista = broker.VratiSve(new Iznajmljivanje());
        List<Iznajmljivanje> lista2 = dblista.stream().map(Iznajmljivanje.class::cast).collect(Collectors.toList());
        KriterijumPretrage kp = (KriterijumPretrage) odo;
        int brojac = kriterijumTip(kp);
        for (Iznajmljivanje el : lista2) {
            if(brojac == 1){
                if(el.getIznajmljivanjeId().equals(new Long(kp.getTekst()))){
                    lista.add(el);
                }
            }else if (brojac ==2){
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(kp.getTekst());
                if(el.getDatumOd().equals(date) || el.getDatumDo().equals(date)){
                    lista.add(el);
                }
            }else{
                if(el.getKorisnik().getKorisnickoIme().toLowerCase().contains(kp.getTekst().toLowerCase()) || el.getAutomobil().getNaziv().toLowerCase().contains(kp.getTekst().toLowerCase())
                        || el.getKlijent().getIme().toLowerCase().contains(kp.getTekst().toLowerCase())){
                    lista.add(el);
                }
            }
        }
    }
    
    private int kriterijumTip(KriterijumPretrage kp){
        try{
            int broj = Integer.parseInt(kp.getTekst());
            return 1;
        }catch(Exception e){
        }
        try{
            Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(kp.getTekst());
            return 2;
        }catch(Exception e){
            
        }
        return 3;
    }
}
