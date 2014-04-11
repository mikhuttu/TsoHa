package Mallit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kilpailu extends KyselyToiminnot {
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
    
    public List<Kilpailu> haeKilpailut() {

       try {
           String sql = "SELECT id, nimi FROM kilpailu ORDER BY nimi";
           alustaKysely(sql);
            
           suoritaKysely();
        
           return palautaKaikki();
       }

       finally {
          lopeta();
       }
    }
    
    public Kilpailu haeKilpailu(int id) {

        try {
            String sql = "SELECT * FROM kilpailu WHERE id = ? LIMIT 1";

            alustaKysely(sql);
            statement.setInt(1, id);
            
            suoritaKysely();
            
            if (results.next()) {
                return palauta();
            }
            
        } catch (SQLException e) {
            Logger.getLogger(Kilpailu.class.getName()).log(Level.SEVERE, null, e);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
    
    public Kilpailu haeKilpailu(String nimi) {

        try {
            String sql = "SELECT * FROM kilpailu WHERE nimi = ? LIMIT 1";

            alustaKysely(sql);
            statement.setString(1, nimi);
            
            suoritaKysely();
            
            if (results.next()) {
                return palauta();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Kilpailu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
    
    private ArrayList<Kilpailu> palautaKaikki() {
        
        ArrayList<Kilpailu> kilpailut = new ArrayList<Kilpailu>();
        
        try {
            while (results.next()) {
                kilpailut.add(palauta());
            }
        }
        catch (SQLException e) {
            Logger.getLogger(Kilpailu.class.getName()).log(Level.SEVERE, null, e);
        }    
        
        return kilpailut;
    }
    
    private Kilpailu palauta() {
        Kilpailu kilpailu = null;
        
        try {
            kilpailu = new Kilpailu();
            
            kilpailu.setId(results.getInt("id"));
            kilpailu.setNimi(results.getString("nimi"));
        }
        
        catch (SQLException ex) {
            Logger.getLogger(Valiaikapiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kilpailu;
    }

    public void poistaKilpailu(int kilpailuId) {
        
        try {
            String sql = "DELETE FROM kilpailu WHERE kilpailu.id = ?";
            alustaKysely(sql);
            
            statement.setInt(1, kilpailuId);
            suoritaKysely();
        }
        
        catch (SQLException e) {
            Logger.getLogger(Osallistuja.class.getName()).log(Level.SEVERE, null, e);
        }
        
        finally {
            lopeta();
        }
    }

    public boolean lisaaKilpailu(String nimi) {
        
        try {
            if (onkoKilpailuJoOlemassa(nimi)) {
                return true;
            }
            
            luoUusiKilpailu(nimi);
            return false;
        }
        
        finally {
            lopeta();
        }
    }
    
    public boolean onOlemassa(String nimi) {
        try {
            return onkoKilpailuJoOlemassa(nimi);
        }
        finally {
            lopeta();
        }
    }
    
    private boolean onkoKilpailuJoOlemassa(String nimi) {
        
        try {
            String sql = "SELECT * FROM kilpailu WHERE nimi = ?";
            
            alustaKysely(sql);

            statement.setString(1, nimi);
            
            suoritaKysely();
            return results.next();
        }
        
        catch (SQLException e) {
            Logger.getLogger(Osallistuja.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }
    
    private void luoUusiKilpailu(String nimi) {
        try {
            String sql = "INSERT INTO kilpailu (nimi) VALUES (?)";
            
            alustaKysely(sql);

            statement.setString(1, nimi);
            
            suoritaKysely();
        }
        
        catch (SQLException e) {
            Logger.getLogger(Osallistuja.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void paivitaNimi(int id, String nimi) {
        try {
            String sql = "UPDATE kilpailu SET nimi = ? WHERE id = ?";
            alustaKysely(sql);

            statement.setString(1, nimi);
            statement.setInt(2, id);
            
            suoritaKysely();
        }
        
        catch (SQLException e) {
            Logger.getLogger(Osallistuja.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public ArrayList<Kilpailu> haeKilpailut(int kilpailijaId) {
        try {
            String sql = "SELECT * FROM kilpailu, osallistuja WHERE kilpailu.id = osallistuja.kilpailu AND osallistuja.kilpailija = ?";
            alustaKysely(sql);
            
            statement.setInt(1, kilpailijaId);
            
            suoritaKysely();
            
            return palautaKaikki();
        }
        catch (SQLException e) {
            Logger.getLogger(Osallistuja.class.getName()).log(Level.SEVERE, null, e);
        }
        
        finally {
            lopeta();
        }
        return null;
    }
}