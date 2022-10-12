/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.Serializable;

/**
 *
 * @author Lenovo
 */
public class Zahtev implements Serializable{
    
    private int operacija;
    private Object objekat;

    public Zahtev() {
    }

    public Zahtev(int operacija, Object objekat) {
        this.operacija = operacija;
        this.objekat = objekat;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Object getObjekat() {
        return objekat;
    }

    public void setObjekat(Object objekat) {
        this.objekat = objekat;
    }
    
    
    
}
