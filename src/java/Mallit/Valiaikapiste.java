package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Valiaikapiste {
    private int id;
    private int numero;
    private int kilpailu;
    private ArrayList<Tulos> tulokset;
    
    public int getId() {
        return this.id;
    }
    
    public int getKilpailu() {
        return this.kilpailu;
    }
    
    public int getNumero() {
        return this.numero;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setKilpailu(int kilpailu) {
        this.kilpailu = kilpailu;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public ArrayList<Tulos> haeTulokset() {
        return new Tulos().haeValiaikapisteenTulokset(this);
    }
    
    public Valiaikapiste haeValiaikapisteTulosTaulunKautta() {
        
        Connection yhteys = null;
        PreparedStatement kysely = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT id, kilpailu FROM valiaikapiste WHERE valiaikapiste.id = ? LIMIT 1";
            
            yhteys = Tietokanta.getYhteys();
            kysely = yhteys.prepareStatement(sql);

            kysely.setString(1, "tulos.valiaikapiste");

            rs = kysely.executeQuery();
            
            Valiaikapiste piste = null;
            
            if (rs.next()) {
                piste = new Valiaikapiste();
                piste.setId(rs.getInt("id"));
                piste.setKilpailu(rs.getInt("kilpailu"));
            }
            
            return piste;
        }
        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            try { rs.close(); } catch (Exception e) {}
            try { kysely.close(); } catch (Exception e) {}
            try { yhteys.close(); } catch (Exception e) {}
        }
        
        return null;
    }
    
    public ArrayList<Valiaikapiste> haeKilpailunValiaikapisteet(Kilpailu kilpailu) {
        
        Connection yhteys = null;
        PreparedStatement kysely = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT DISTINCT id, numero, kilpailu FROM valiaikapiste WHERE valiaikapiste.kilpailu = ?";
            
            yhteys = Tietokanta.getYhteys();
            kysely = yhteys.prepareStatement(sql);

            kysely.setInt(1, kilpailu.getId());

            rs = kysely.executeQuery();
            
            ArrayList<Valiaikapiste> valiaikapisteet = new ArrayList<Valiaikapiste>();
            
            while (rs.next()) {
                
                Valiaikapiste piste = new Valiaikapiste();
                piste.setId(rs.getInt("id"));
                piste.setNumero(rs.getInt("numero"));
                piste.setKilpailu(rs.getInt("kilpailu"));
                
                valiaikapisteet.add(piste);
            }
            
            return valiaikapisteet;
        }
        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            try { rs.close(); } catch (Exception e) {}
            try { kysely.close(); } catch (Exception e) {}
            try { yhteys.close(); } catch (Exception e) {}
        }
        
        return null;
    }
}