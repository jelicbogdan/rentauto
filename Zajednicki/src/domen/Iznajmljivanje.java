/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Iznajmljivanje extends OpstiDomenskiObjekat implements Serializable{
    
    private Long iznajmljivanjeId; 
    private Automobil automobil; 
    private Klijent klijent; 
    private Date datumOd;
    private Date datumDo;
    private double iznos;
    private double cena;   
    private Korisnik korisnik; 

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(Long iznajmljivanjeId, Automobil automobil, Klijent klijent, Date datumOd, Date datumDo, double iznos, double cena, Korisnik korisnik) {
        this.iznajmljivanjeId = iznajmljivanjeId;
        this.automobil = automobil;
        this.klijent = klijent;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.iznos = iznos;
        this.cena = cena;
        this.korisnik = korisnik;
    }

    public Long getIznajmljivanjeId() {
        return iznajmljivanjeId;
    }

    public void setIznajmljivanjeId(Long iznajmljivanjeId) {
        this.iznajmljivanjeId = iznajmljivanjeId;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        if (!Objects.equals(this.automobil, other.automobil)) {
            return false;
        }
        if (!Objects.equals(this.klijent, other.klijent)) {
            return false;
        }
        return true;
    }

    
   

    @Override
    public String toString() {
        return "Iznajmljivanje{" + "iznajmljivanjeId=" + iznajmljivanjeId + ", automobil=" + automobil + ", klijent=" + klijent + ", datumOd=" + datumOd + ", datumDo=" + datumDo + ", iznos=" + iznos + ", cena=" + cena + ", korisnik=" + korisnik + '}';
    }
    
    

    @Override
    public String NazivTabele() {
        
        return "Iznajmljivanje";
    }

    @Override
    public String NazivTabeleVise() {
        
            return "Iznajmljivanje i ";
    }

    @Override
    public String Dodaj() {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%d, %d, \"%s\",\"%s\",%f,%f,%d",this.automobil.getAutomobilID(),
                this.klijent.getKlijentId(),date.format(datumOd),date.format(datumDo),this.iznos, this.cena, this.korisnik.getKorisnikId());
            
    }

    @Override
    public String KoloneDodaj() {
        return "automobilID,klijentID,datumOd,datumDo,iznos,cena,korisnikID";    }

    @Override
    public String Izmeni() {
DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("automobilID = %d,klijentId = %d, datumOd=\"%s\",datumDo = \"%s\",iznos = %f, cena = %f, korisnikId = %d",this.automobil.getAutomobilID(),
                this.klijent.getKlijentId(),date.format(datumOd),date.format(datumDo),this.iznos, this.cena, this.korisnik.getKorisnikId());    }

    @Override
    public String Join() {
 return "join Automobil a on (a.automobilID = i.automobilID) join Klijent k1 on (k1.klijentID= i.klijentID) join Korisnik k2 on (k2.korisnikID = i.korisnikID)";    }

    @Override
    public String WhereUpit() {
return String.format("iznajmljivanjeId = %d", this.iznajmljivanjeId);    }

    @Override
    public List<OpstiDomenskiObjekat> VratiListuDomenskih(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                
                Long automobilID = rs.getLong("automobilId");
                String naziv = rs.getString("naziv");
                Automobil a = new Automobil();
                a.setAutomobilID(automobilID);
                a.setNaziv(naziv);
               
                Long korisnikId = rs.getLong("korisnikId");
                String korisnicko  = rs.getString("korisnickoIme");
                Korisnik k = new Korisnik();
                k.setKorisnikId(korisnikId);
                k.setKorisnickoIme(korisnicko);
                
                Long iznajmId = rs.getLong("iznajmljivanjeId");
                Date datumOd = rs.getDate("datumOd");
                Date datumDo = rs.getDate("datumDo");
                double iznos = rs.getDouble("iznos");
                double cena = rs.getDouble("cena");
                
                Long klijentId = rs.getLong("klijentId");
                String ime  = rs.getString("ime");
                
                Klijent kli = new Klijent(klijentId, "", ime, "");
                
                Iznajmljivanje i = new Iznajmljivanje(iznajmId, a, kli, datumOd, datumDo,iznos, cena, k);
                
               
                lista.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Iznajmljivanje.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;    }
    
    
}
