/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broker;

import domen.OpstiDomenskiObjekat;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Lenovo
 */
public class BrokerBaze {
    
    private Connection connection;
    
    private String korisnicko;
    private String pass;
    private String driver;
    private String url;
    
  

    public BrokerBaze() {
      this.pocetnaKonfiguracija();

    }
    
    public void connect() throws Exception {
        try {
            Class.forName(this.driver);
            String url = "jdbc:mysql://localhost:3307/rentauto";
            connection = DriverManager.getConnection(url, korisnicko, pass);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Error");
        }
    }
    private void pocetnaKonfiguracija() {
        try {
            Properties properties = new Properties();
            String propertiesFileName = "konfiguracija/db.properties";
            FileInputStream fileInputStream = new FileInputStream(propertiesFileName);
            properties.load(fileInputStream);
            this.url = properties.getProperty("url");
            this.driver = properties.getProperty("driver");
            this.korisnicko = properties.getProperty("user");
            this.pass = properties.getProperty("password");

            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } 
     public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Error");
            }
        }
    }

    public void commit() throws Exception {
        if (connection != null) {
            try {
                this.connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Error");
            }
        }
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Error");
            }
        }
    }


    public List<OpstiDomenskiObjekat> VratiSve(OpstiDomenskiObjekat object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + object.NazivTabeleVise()+ " "+ object.Join()+ ";";
            ResultSet rs = statement.executeQuery(query);
            return object.VratiListuDomenskih(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public Long DodajDomenskiObjekat(OpstiDomenskiObjekat object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO " + object.NazivTabele()+ "(" + object.KoloneDodaj()+ ")" + " VALUES (" + object.Dodaj()+ ")";
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return -1l;
    }

    

    public int IzmeniDomenskiObjekat(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE " + odo.NazivTabele()+ " SET " + odo.Izmeni()+ " WHERE " + odo.WhereUpit();
            System.out.println(query);
            int rowsUpdated = statement.executeUpdate(query);
            return rowsUpdated;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public int ObrisiDomenskiObjekat(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + odo.NazivTabele()+ " WHERE " + odo.WhereUpit();
            int rowsUpdated = statement.executeUpdate(query);
            return rowsUpdated;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
}
