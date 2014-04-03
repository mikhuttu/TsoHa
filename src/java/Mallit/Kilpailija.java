package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kilpailija {
    private int id;
    private String nimi;
    
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
    
    public ArrayList<Kilpailija> haeKilpailijat() {
        return haeKilpailunKilpailijat(null);
    }
    
    public ArrayList<Kilpailija> haeKilpailunKilpailijat(Kilpailu kilpailu) {
        
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
            
            ArrayList<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
            
            while (tulokset.next()) {
                
                Kilpailija k = new Kilpailija();
                k.setId(tulokset.getInt("id"));
                k.setNimi(tulokset.getString("nimi"));

                kilpailijat.add(k);
            }
            
            try { tulokset.close(); } catch (SQLException e) {}
            try { kysely.close(); } catch (SQLException e) {}
            try { yhteys.close(); } catch (SQLException e) {}
            
            return kilpailijat;
            
        } catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public Kilpailija haeKilpailijaTulosTaulunKautta() {
        
        try {
            String sql = "SELECT id, nimi FROM kilpailija WHERE kilpailija.id = ? ' LIMIT = 1";
            
            Connection yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(sql);
            
            kysely.setString(1, "tulos.kilpailija");
            ResultSet tulokset = kysely.executeQuery();
            
            Kilpailija k =  null;
            
            if (tulokset.next()) {
                k = new Kilpailija();
                k.setId(tulokset.getInt("id"));
                k.setNimi(tulokset.getString("nimi"));
            }
            
            try { tulokset.close(); } catch (SQLException e) {}
            try { kysely.close(); } catch (SQLException e) {}
            try { yhteys.close(); } catch (SQLException e) {}
            
            return k;
        }
        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}