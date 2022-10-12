/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public abstract class OpstiDomenskiObjekat {
    
    public abstract String NazivTabele();
    public abstract String NazivTabeleVise();
    public abstract String Dodaj();
    public abstract String KoloneDodaj();
    public abstract String Izmeni();
    public abstract String Join();
    public abstract String WhereUpit();
    public abstract List<OpstiDomenskiObjekat> VratiListuDomenskih(ResultSet rs);
    
}
