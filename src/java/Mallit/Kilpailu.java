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
            String sql = "SELECT id, nimi FROM kilpailu";
            
            alustaKysely(sql);
            suoritaKysely();
            
            ArrayList<Kilpailu> kilpailut = new ArrayList<Kilpailu>();
            
            while (results.next()) {
                kilpailut.add(palautaKilpailu());
            }
            
            return kilpailut;
            
        } catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
    
    public Kilpailu haeKilpailu(int id) {

        try {
            String sql = "SELECT * FROM kilpailu WHERE id = ? LIMIT 1";

            alustaKysely(sql);
            statement.setInt(1, id);
            
            suoritaKysely();
            
            if (results.next()) {
                return palautaKilpailu();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Kilpailu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
    
    private Kilpailu palautaKilpailu() {
        try {
            Kilpailu kilpailu = new Kilpailu();
            
            kilpailu.setId(results.getInt("id"));
            kilpailu.setNimi(results.getString("nimi"));
            
            return kilpailu;
        }
        
        catch (SQLException ex) {
            Logger.getLogger(Valiaikapiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
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
}