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
public class Odgovor implements Serializable{
    
    private boolean status;
    private Object objekat;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getPodaci() {
        return objekat;
    }

    public void setPodaci(Object podaci) {
        this.objekat = podaci;
    }

    public Odgovor(boolean status, Object podaci) {
        this.status = status;
        this.objekat = podaci;
    }
    
    
}
