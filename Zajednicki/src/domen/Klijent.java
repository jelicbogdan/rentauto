/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Klijent extends OpstiDomenskiObjekat implements Serializable{
    
    private Long klijentId; 
    private String jmbg;
    private String ime;
    private String drzava; 

    public Klijent() {
    }

    public Klijent(Long klijentId, String jmbg, String ime, String drzava) {
        this.klijentId = klijentId;
        this.jmbg = jmbg;
        this.ime = ime;
        this.drzava = drzava;
    }

    public Long getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(Long klijentId) {
        this.klijentId = klijentId;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klijent other = (Klijent) obj;
        if (!Objects.equals(this.klijentId, other.klijentId)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return ime ;
    }

   
    
    @Override
    public String NazivTabele() {
        
            return "Klijent";
    }

    @Override
    public String NazivTabeleVise() {
        
            return "Klijent k ";
    }

    @Override
    public String Dodaj() {
         return String.format("\"%s\", \"%s\",\"%s\" ",this.jmbg,this.ime,this.drzava);
            
    }

    @Override
    public String KoloneDodaj() {
        
          return "jmbg,ime,drzava";
    }

    @Override
    public String Izmeni() {
        return "";
    }

    @Override
    public String Join() {
        return "";
    }

    @Override
    public String WhereUpit() {
                return String.format("klijentId = %d", this.klijentId);
    }

    @Override
    public List<OpstiDomenskiObjekat> VratiListuDomenskih(ResultSet rs) {
        
            List<OpstiDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                Long id = rs.getLong("klijentId");
                String ime  = rs.getString("ime");            
                String jmbg  = rs.getString("jmbg");
                String drzava  = rs.getString("drzava");
                Klijent kli = new Klijent(id, jmbg, ime, drzava);
                lista.add(kli);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
