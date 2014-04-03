package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Valiaikapiste {
    private int id;
    private int kilpailu;
    private ArrayList<Tulos> tulokset;
    
    public int getId() {
        return this.id;
    }
    
    public int getKilpailu() {
        return this.kilpailu;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setKilpailu(int kilpailu) {
        this.kilpailu = kilpailu;
    }
    
    public ArrayList<Tulos> haeTulokset() {
        tulokset = new Tulos().haeValiaikapisteenTulokset(this);
        jarjestaTulokset();
        
        return this.tulokset;
    }
    
    public Valiaikapiste haeValiaikapisteTulosTaulunKautta() {
        
        try {
            String sql = "SELECT id, kilpailu FROM valiaikapiste WHERE valiaikapiste.id = ? ' LIMIT = 1";
            
            Connection yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(sql);

            kysely.setString(1, "tulos.valiaikapiste");

            ResultSet rs = kysely.executeQuery();
            
            Valiaikapiste piste = null;
            
            if (rs.next()) {
                piste = new Valiaikapiste();
                piste.setId(rs.getInt("id"));
                piste.setKilpailu(rs.getInt("kilpailu"));
            }
            
            try { rs.close(); } catch (SQLException e) {}
            try { kysely.close(); } catch (SQLException e) {}
            try { yhteys.close(); } catch (SQLException e) {}
            
            return piste;
        }
        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Valiaikapiste> haeKilpailunValiaikapisteet(Kilpailu kilpailu) {
        
        try {
            String sql = "SELECT id, kilpailu FROM valiaikapiste WHERE valiaikapiste.kilpailu = ?";
            
            Connection yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(sql);

            kysely.setString(1, "kilpailu.id");

            ResultSet rs = kysely.executeQuery();
            
            ArrayList<Valiaikapiste> valiaikapisteet = new ArrayList<Valiaikapiste>();
            
            while (rs.next()) {
                
                Valiaikapiste piste = new Valiaikapiste();
                piste.setId(rs.getInt("id"));
                piste.setKilpailu(rs.getInt("kilpailu"));
            }
            
            try { rs.close(); } catch (SQLException e) {}
            try { kysely.close(); } catch (SQLException e) {}
            try { yhteys.close(); } catch (SQLException e) {}
            
            return valiaikapisteet;
        }
        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        
        
    }
    
    public void jarjestaTulokset() {
        Collections.sort(tulokset);
    }
}