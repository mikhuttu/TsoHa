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

public class Kilpailu {
    private int id;
    private String nimi;
    private ArrayList<Kilpailija> kilpailijat;
    private ArrayList<Valiaikapiste> valiaikapisteet;
    
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
        this.kilpailijat = new Kilpailija().haeKilpailunKilpailijat(this);
    }
    
    public List<Kilpailija> haeKilpailijat() {
        return new Kilpailija().haeKilpailunKilpailijat(this);
    }
    
    public List<Valiaikapiste> haeValiaikapisteet() {
        return new Valiaikapiste().haeKilpailunValiaikapisteet(this);
    }
    
    public List<Tulos> haeTulokset(int valiaikapiste) {
        return valiaikapisteet.get(valiaikapiste).haeTulokset();
    }
    
    public List<Tulos> haeLoppuTulokset() {
        return haeTulokset(valiaikapisteet.size() - 1);
    }
    
    public void lisaaKilpailija(Kilpailija kilpailija) {
        kilpailijat.add(kilpailija);
    }
    
    public void lisaaValiaikapiste(Valiaikapiste valiaikapiste) {
        valiaikapisteet.add(valiaikapiste);
    }
    
    
    public List<Kilpailu> haeKilpailut() {
        try {
            String sql = "SELECT id, nimi FROM kilpailu";
            
            Connection yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(sql);
            ResultSet tulokset = kysely.executeQuery();
            
            ArrayList<Kilpailu> kilpailut = new ArrayList<Kilpailu>();
            
            while (tulokset.next()) {
                
                Kilpailu k = new Kilpailu();
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
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Kilpailu haeKilpailu(int id) {
        Connection yhteys = null;
        
        try {
            String sql = "SELECT * FROM kilpailu WHERE id = ? ' LIMIT = 1";

            yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(sql);
            kysely.setInt(1, id);
            
            ResultSet tulokset = kysely.executeQuery();
            
            Kilpailu kilpailu = null;
            
            if (tulokset.next()) {
                kilpailu = new Kilpailu();
                kilpailu.setId(tulokset.getInt("id"));
                kilpailu.setNimi(tulokset.getString("nimi"));
                kilpailu.setKilpailijat();
            }
            
            return kilpailu;
            
        } catch (SQLException ex) {
            Logger.getLogger(Kilpailu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            if (yhteys != null) {
                try { yhteys.close(); } catch (SQLException e) {}
            }  
        }
        return null;
    }
}