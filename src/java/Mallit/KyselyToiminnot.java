package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KyselyToiminnot {
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet results;
    
    protected void alustaKysely(String sql) {
        connection = null;
        statement = null;
        results = null;
        
        connection = yhteys();
        statement = kysely(sql);
    }
    
    protected Connection yhteys() {
        try {
            return Tietokanta.getYhteys();
        } 
        catch (SQLException e) {
            Logger.getLogger(KyselyToiminnot.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    protected PreparedStatement kysely(String sql) {
        try {
            return connection.prepareStatement(sql);
        } 
        catch (SQLException e) {
            Logger.getLogger(KyselyToiminnot.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    protected void suoritaKysely() {
        try {
            results = statement.executeQuery();
        }   
        catch (SQLException ex) {
            Logger.getLogger(KyselyToiminnot.class.getName()).log(Level.SEVERE, null, ex);
            results = null;
        }
    }
    
    protected void lopeta() {
        try { results.close(); } catch (Exception e) {}
        try { statement.close(); } catch (Exception e) {}
        try { connection.close(); } catch (Exception e) {}
    }
}
