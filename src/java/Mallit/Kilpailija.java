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
    
    public ArrayList<Kilpailija> haeKilpailunKilpailijat(Kilpailu kilpailu) {
        
        Connection yhteys = null;
        PreparedStatement kysely = null;
        ResultSet tulokset = null;
        
        try {
            String sql = "SELECT kilpailija.id, kilpailija.nimi FROM kilpailija, osallistuja WHERE kilpailija.id = osallistuja.kilpailija AND osallistuja.kilpailu = ?";
            
            yhteys = Tietokanta.getYhteys();
            kysely = yhteys.prepareStatement(sql);
            
            kysely.setInt(1, kilpailu.getId());

            tulokset = kysely.executeQuery();
            
            ArrayList<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
            
            while (tulokset.next()) {
                
                Kilpailija k = new Kilpailija();
                k.setId(tulokset.getInt("id"));
                k.setNimi(tulokset.getString("nimi"));

                kilpailijat.add(k);
            }
            
            return kilpailijat;
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            try { tulokset.close(); } catch (Exception e) {}
            try { kysely.close(); } catch (Exception e) {}
            try { yhteys.close(); } catch (Exception e) {}
        }
        
        return null;
    }
    
    
    public Kilpailija haeKilpailijaTulosTaulunKautta() {
        
        Connection yhteys = null;
        PreparedStatement kysely = null;
        ResultSet tulokset = null;
        
        try {
            String sql = "SELECT id, nimi FROM kilpailija WHERE kilpailija.id = ? LIMIT 1";
            
            yhteys = Tietokanta.getYhteys();
            kysely = yhteys.prepareStatement(sql);
            
            kysely.setString(1, "tulos.kilpailija");
            tulokset = kysely.executeQuery();
            
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
        
        finally {
            try { tulokset.close(); } catch (Exception e) {}
            try { kysely.close(); } catch (Exception e) {}
            try { yhteys.close(); } catch (Exception e) {}
        }
        
        return null;
    }

    public ArrayList<Kilpailija> haeKaikkiJotkaEivatOsallistu(Kilpailu kilpailu) {
        
        Connection yhteys = null;
        PreparedStatement kysely = null;
        ResultSet tulokset = null;
        
        try {
            String sql = "SELECT kilpailija.id, kilpailija.nimi FROM kilpailija, osallistuja WHERE kilpailija.id = osallistuja.kilpailija AND osallistuja.kilpailu != ?";
            
            yhteys = Tietokanta.getYhteys();
            kysely = yhteys.prepareStatement(sql);
            
            kysely.setInt(1, kilpailu.getId());

            tulokset = kysely.executeQuery();
            
            ArrayList<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
            
            while (tulokset.next()) {
                
                Kilpailija k = new Kilpailija();
                k.setId(tulokset.getInt("id"));
                k.setNimi(tulokset.getString("nimi"));

                kilpailijat.add(k);
            }
            
            return kilpailijat;
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            try { tulokset.close(); } catch (Exception e) {}
            try { kysely.close(); } catch (Exception e) {}
            try { yhteys.close(); } catch (Exception e) {}
        }
        
        return null;
    }
}