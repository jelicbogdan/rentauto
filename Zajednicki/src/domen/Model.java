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
public class Model extends OpstiDomenskiObjekat implements Serializable{
    
    private Long modelID;
    private String nazivModela;
    private String opis;

    public Model() {
    }

    public Model(Long modelID, String nazivModela, String opis) {
        this.modelID = modelID;
        this.nazivModela = nazivModela;
        this.opis = opis;
    }

    public Long getModelID() {
        return modelID;
    }

    public void setModelID(Long modelID) {
        this.modelID = modelID;
    }

    public String getNazivModela() {
        return nazivModela;
    }

    public void setNazivModela(String nazivModela) {
        this.nazivModela = nazivModela;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return  nazivModela;
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
        final Model other = (Model) obj;
        if (!Objects.equals(this.modelID, other.modelID)) {
            return false;
        }
        return true;
    }

   

    
    
    @Override
    public String NazivTabele() {
            return "Model ";
    }

    @Override
    public String NazivTabeleVise() {
return "Model m ";
    }

    @Override
    public String Dodaj() {
return "";
    }

    @Override
    public String KoloneDodaj() {
return "";    }

    @Override
    public String Izmeni() {
return "";    }

    @Override
    public String Join() {
return "";    }

    @Override
    public String WhereUpit() {
        return String.format("modelID = %d", this.getModelID());    
    }

    @Override
    public List<OpstiDomenskiObjekat> VratiListuDomenskih(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                Long modelId = rs.getLong("modelID");
                String nazivModela  = rs.getString("naziv");
                String opis  = rs.getString("opis");
                Model m = new Model(modelId, nazivModela, opis);
                lista.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;    
    }
    
    
    
}
