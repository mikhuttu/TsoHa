package Mallit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kilpailija extends KyselyToiminnot {
    private int id;
    private String nimi;
    
    public Kilpailija() {
    }
    
    public Kilpailija(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }
    
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
    
    public Kilpailija haeKilpailija(int id) {

        try {
            String sql = "SELECT * FROM kilpailija WHERE id = ? LIMIT 1";

            alustaKysely(sql);
            statement.setInt(1, id);
            
            suoritaKysely();
            
            Kilpailija kilpailija = null;
            
            if (results.next()) {
                kilpailija = new Kilpailija();
                
                kilpailija.setId(results.getInt("id"));
                kilpailija.setNimi(results.getString("nimi"));
            }
            
            return kilpailija;
            
        } catch (SQLException ex) {
            Logger.getLogger(Kilpailu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
    
    public ArrayList<Kilpailija> haeKilpailunKilpailijat(Kilpailu kilpailu) {

        try {
            String sql = "SELECT kilpailija.id, kilpailija.nimi "
                       + "FROM kilpailija, osallistuja WHERE kilpailija.id = osallistuja.kilpailija AND osallistuja.kilpailu = ? "
                       + "ORDER BY nimi asc";
            
            alustaKysely(sql);
            statement.setInt(1, kilpailu.getId());

            suoritaKysely();
            
            ArrayList<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
            
            while (results.next()) {
                Kilpailija k = new Kilpailija();
                k.setId(results.getInt("id"));
                k.setNimi(results.getString("nimi"));

                kilpailijat.add(k);
            }
            
            return kilpailijat;
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
    
    
    public Kilpailija haeKilpailijaTulosTaulunKautta() {
        
        try {
            String sql = "SELECT id, nimi FROM kilpailija WHERE kilpailija.id = ? LIMIT 1";
            
            alustaKysely(sql);
            statement.setString(1, "tulos.kilpailija");
            
            suoritaKysely();
            
            Kilpailija kilpailija =  null;
            
            if (results.next()) {
                kilpailija = new Kilpailija();
                kilpailija.setId(results.getInt("id"));
                kilpailija.setNimi(results.getString("nimi"));
            }
            
            return kilpailija;
        }
        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }

    public ArrayList<Kilpailija> haeKaikkiJotkaEivatOsallistu(Kilpailu kilpailu) {
        
        try {
            String sql = "SELECT kilpailija.id, kilpailija.nimi FROM kilpailija WHERE"
                   + " kilpailija.id NOT IN"
                   + " (SELECT osallistuja.kilpailija FROM osallistuja WHERE osallistuja.kilpailu = ?) ORDER BY nimi asc";
            
            alustaKysely(sql);
            statement.setInt(1, kilpailu.getId());
            //statement.setInt(2, kilpailu.getId());

            suoritaKysely();
            
            ArrayList<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
            
            while (results.next()) {
                Kilpailija kilpailija = new Kilpailija();
                kilpailija.setId(results.getInt("id"));
                kilpailija.setNimi(results.getString("nimi"));

                kilpailijat.add(kilpailija);
            }
            
            return kilpailijat;
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
}