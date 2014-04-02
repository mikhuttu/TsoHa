package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KilpailuMalli {
    private int id;
    private String nimi;
    private List<KilpailijaMalli> kilpailijat;
    
    public int getId() {
        return this.id;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public void setKilpailijat() {
        this.kilpailijat = KilpailijaMalli.getKilpailijat(this);
    }
    
    public List<KilpailijaMalli> getKilpailijat() {
        return KilpailijaMalli.getKilpailijat(this);
    }
    
    public static List<KilpailuMalli> getKilpailut() {
        try {
            String sql = "SELECT id, nimi FROM kilpailu";
            
            Connection yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(sql);
            ResultSet tulokset = kysely.executeQuery();
            
            List<KilpailuMalli> kilpailut = null;
            
            while (tulokset.next()) {
                kilpailut = new ArrayList<KilpailuMalli>();
                
                KilpailuMalli k = new KilpailuMalli();
                k.setId(tulokset.getInt("id"));
                k.setNimi(tulokset.getString("nimi"));
                k.setKilpailijat();
                
                kilpailut.add(k);
            }
            
            try { tulokset.close(); } catch (SQLException e) {}
            try { kysely.close(); } catch (SQLException e) {}
            try { yhteys.close(); } catch (SQLException e) {}
            
            return kilpailut;
            
        } catch (SQLException ex) {
            Logger.getLogger(KilpailijaMalli.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static KilpailuMalli etsi(int id) {
        try {
            
            String sql = "SELECT * FROM kilpailu WHERE id = ? ' LIMIT = 1";
            
            Connection yhteys = Tietokanta.getYhteys();

            PreparedStatement kysely = yhteys.prepareStatement(sql);
            kysely.setInt(1, id);
            
            ResultSet tulokset = kysely.executeQuery();
            
            KilpailuMalli kilpailu = null;
            
            if (tulokset.next()) {
                kilpailu = new KilpailuMalli();
                kilpailu.setId(tulokset.getInt("id"));
                kilpailu.setNimi(tulokset.getString("nimi"));
                kilpailu.setKilpailijat();
            }
            
            try { tulokset.close(); } catch (SQLException e) {}
            try { kysely.close(); } catch (SQLException e) {}
            try { yhteys.close(); } catch (SQLException e) {}

            return kilpailu;
        }
        catch (SQLException ex) {
            Logger.getLogger(KilpailuMalli.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}