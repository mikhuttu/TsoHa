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

public class KilpailijaMalli {
    private final int id;
    private final String nimi;
    
    public KilpailijaMalli(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public static List<KilpailijaMalli> getKilpailijat() {
        return getKilpailijat(null);
    }
    
    public static List<KilpailijaMalli> getKilpailijat(KilpailuMalli kilpailu) {
        
        try {
            String sql = "SELECT id, nimi FROM kilpailija WHERE kilpailija.id = ?";
            Connection yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(sql);
            
            if (kilpailu != null) {
                kysely.setString(1, "osallistuja.kilpailija AND osallistuja.kilpailu = " + kilpailu.getId());
            }
            else {
                kysely.setString(1, "'");       // mikä tahansa kilpailija kelpaa
            }
            
            ResultSet tulokset = kysely.executeQuery();
            
            List<KilpailijaMalli> kilpailijat = null;
            
            while (tulokset.next()) {
                kilpailijat = new ArrayList<KilpailijaMalli>();
                
                KilpailijaMalli k = new KilpailijaMalli(tulokset.getInt("id"), tulokset.getString("nimi"));
                kilpailijat.add(k);
            }
            
            try { tulokset.close(); } catch (SQLException e) {}
            try { kysely.close(); } catch (SQLException e) {}
            try { yhteys.close(); } catch (SQLException e) {}
            
            return kilpailijat;
            
        } catch (SQLException ex) {
            Logger.getLogger(KilpailijaMalli.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}