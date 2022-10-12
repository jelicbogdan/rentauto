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
public class Automobil extends OpstiDomenskiObjekat implements Serializable{
    
    private Long automobilID;
    private String naziv;
    private String vlasnik;
    private String opis;
    private double cenaPoDanu;
    private Model tipAutomobilaID;

    public Automobil() {
    }

    public Automobil(Long automobilID, String naziv, String vlasnik, String opis, double cenaPoDanu, Model tipAutomobilaID) {
        this.automobilID = automobilID;
        this.naziv = naziv;
        this.vlasnik = vlasnik;
        this.opis = opis;
        this.cenaPoDanu = cenaPoDanu;
        this.tipAutomobilaID = tipAutomobilaID;
    }

    public Long getAutomobilID() {
        return automobilID;
    }

    public void setAutomobilID(Long automobilID) {
        this.automobilID = automobilID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(String vlasnik) {
        this.vlasnik = vlasnik;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    public Model getTipAutomobilaID() {
        return tipAutomobilaID;
    }

    public void setTipAutomobilaID(Model tipAutomobilaID) {
        this.tipAutomobilaID = tipAutomobilaID;
    }
    
    @Override
    public String toString() {
        return naziv;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
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
        final Automobil other = (Automobil) obj;
        if (!Objects.equals(this.automobilID, other.automobilID)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String NazivTabele() {
        
             return "AUTOMOBIL";
        
    }

    @Override
    public String NazivTabeleVise() {
          return "AUTOMOBIL A";
    }

    @Override
    public String Dodaj() {
        
             return String.format("\"%s\", \"%s\", \"%s\",%f,%d",
                     this.naziv,this.vlasnik,this.opis,this.cenaPoDanu,this.tipAutomobilaID.getModelID());
    }

    @Override
    public String KoloneDodaj() {
        
            
         return "naziv,vlasnik,opis,cenaPoDanu,tipAutomobilaID";
    }

    @Override
    public String Izmeni() {
        
         return String.format("naziv = \"%s\",vlasnik=  \"%s\",opis = \"%s\",cenaPoDanu = %f,tipAutomobilaID = %d",this.naziv,this.vlasnik,this.opis,this.cenaPoDanu,this.tipAutomobilaID.getModelID());    
        
    }

    @Override
    public String Join() {
        return "join Model m on (a.tipAutomobilaID = m.modelID)";
            
    }

    @Override
    public String WhereUpit() {
         return String.format("automobilID = %d", this.automobilID);
        
    }

    @Override
    public List<OpstiDomenskiObjekat> VratiListuDomenskih(ResultSet rs) {
        
        List<OpstiDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                Long id = rs.getLong("automobilID");
                String naziv = rs.getString("naziv");
                String vlasnik = rs.getString("vlasnik");
                String opis = rs.getString("opis");
                double cena = rs.getDouble("cenaPoDanu");               
                Long tipID = rs.getLong("modelID");
                String nazivTipa  = rs.getString("m.naziv");
                
                Model m = new Model(tipID, nazivTipa,"");
                Automobil a = new Automobil(id, naziv, vlasnik, opis, cena, m);
                
                lista.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Automobil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;    
        
    }
    
    
}
