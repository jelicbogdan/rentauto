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
public class Korisnik extends OpstiDomenskiObjekat implements Serializable{
    
    private Long korisnikId; 
    private String ime; 
    private String prezime; 
    private String korisnickoIme; 
    private String korisnickaLozinka; 

    public Korisnik() {
    }

    public Korisnik(Long korisnikId, String ime, String prezime, String korisnickoIme, String korisnickaLozinka) {
        this.korisnikId = korisnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.korisnickaLozinka = korisnickaLozinka;
    }

    @Override
    public String toString() {
        return korisnickoIme;
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
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        if (!Objects.equals(this.korisnickaLozinka, other.korisnickaLozinka)) {
            return false;
        }
        if (!Objects.equals(this.korisnikId, other.korisnikId)) {
            return false;
        }
        return true;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaLozinka() {
        return korisnickaLozinka;
    }

    public void setKorisnickaLozinka(String korisnickaLozinka) {
        this.korisnickaLozinka = korisnickaLozinka;
    }
    
    @Override
    public String NazivTabele() {
        return "Korisnik";
    }

    @Override
    public String NazivTabeleVise() {
        return "Korisnik k";
    }

    @Override
    public String Dodaj() {
        return "";
    }

    @Override
    public String KoloneDodaj() {
        return "";
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
        return String.format("korisnikId = %d", this.korisnikId);
    }
    
    @Override
    public List<OpstiDomenskiObjekat> VratiListuDomenskih(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                Long id = rs.getLong("korisnikId");
                String ime  = rs.getString("imeKorisnika");
                String prezime  = rs.getString("prezimeKorisnika");
                String korisnicko  = rs.getString("korisnickoIme");
                String lozinka  = rs.getString("korisnickaLozinka");
                Korisnik kor = new Korisnik(id, ime, prezime, korisnicko, lozinka);
                lista.add(kor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
