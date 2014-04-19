package Mallit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Malli joka sisältää tietoa kilpailijoista sekä tekee niiden hakuun liittyviä SQL-kyselyjä.
 */

public class Kilpailija extends KyselyToiminnot {
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
    
    /**
    * Palauttaa kaikki tietokannasta haetut kilpailijat.
    */
    
    private ArrayList<Kilpailija> palautaKaikki() {
        ArrayList<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
        
        try {
            while (results.next()) {
                kilpailijat.add(palauta());
            }
        }   
        catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kilpailijat;
    }
    
    /**
    * Palauttaa seuraavana "results":ssa olevan kilpailijan.
    */
    
    private Kilpailija palauta() {
        Kilpailija kilpailija = null;
        
        try {
            kilpailija = new Kilpailija();
            
            kilpailija.setId(results.getInt("id"));
            kilpailija.setNimi(results.getString("nimi"));
        }
        catch (SQLException e) {
            Logger.getLogger(Kilpailu.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return kilpailija;
    }
    
    public Kilpailija haeKilpailija(int id) {

        try {
            String sql = "SELECT * FROM kilpailija WHERE id = ? LIMIT 1";

            alustaKysely(sql);
            statement.setInt(1, id);
            
            suoritaKysely();
            
            if (results.next()) {
                return palauta();
            }
        }
        catch (SQLException e) {
            Logger.getLogger(Kilpailu.class.getName()).log(Level.SEVERE, null, e);
        }

        finally {
            lopeta();
        }
        
        return null;
    }
    
    /**
    * Hakee kaikki kilpailijat nimen mukaan aakkosjärjestyksessä.
    */
    
    public ArrayList<Kilpailija> haeKilpailijat() {
        
        try {
            String sql = "SELECT * FROM kilpailija ORDER BY nimi asc";
            alustaKysely(sql);
            
            suoritaKysely();
       
            return palautaKaikki();
        }

        finally {
            lopeta();
        }
    }
    
    /**
    * Palauttaa kaikki kilpailijat jotka osallistuvat kilpailuun.
    * @param kilpailu
    */
    
    public ArrayList<Kilpailija> haeKilpailunKilpailijat(Kilpailu kilpailu) {

        try {
            String sql = "SELECT kilpailija.id, kilpailija.nimi "
                       + "FROM kilpailija, osallistuja WHERE kilpailija.id = osallistuja.kilpailija AND osallistuja.kilpailu = ? "
                       + "ORDER BY nimi asc";
            
            alustaKysely(sql);
            statement.setInt(1, kilpailu.getId());

            suoritaKysely();

            return palautaKaikki();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        return null;
    }
    
    /**
    * Palauttaa kaikki kilpailijat jotka eivät osallistu kilpailuun.
    * @param kilpailu
    */
    
    public ArrayList<Kilpailija> haeKaikkiJotkaEivatOsallistu(Kilpailu kilpailu) {
        
        try {
            String sql = "SELECT kilpailija.id, kilpailija.nimi FROM kilpailija WHERE"
                   + " kilpailija.id NOT IN"
                   + " (SELECT osallistuja.kilpailija FROM osallistuja WHERE osallistuja.kilpailu = ?) ORDER BY nimi asc";
            
            alustaKysely(sql);
            statement.setInt(1, kilpailu.getId());

            suoritaKysely();
            
            return palautaKaikki();    
        } 
        catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
    
    public void lisaaKilpailija(String nimi) {
        try {
            String sql = "INSERT INTO kilpailija (nimi) VALUES (?)";
            alustaKysely(sql);
            
            statement.setString(1, nimi);
            suoritaKysely();
        }
        catch (SQLException e) {
            Logger.getLogger(Osallistuja.class.getName()).log(Level.SEVERE, null, e);
        }
        
        finally {
            lopeta();
        }
    }
    
    /**
     * Päivittää kilpailijan nimen, jonka id on kyseessä.
     * @param id = kilpailijan id
     * @param uusi = päivitettävä nimi
     */
    
    public void paivitaNimi(int id, String uusi) {
        try {
            String sql = "UPDATE kilpailija SET nimi = ? WHERE id = ?";
            alustaKysely(sql);
            
            statement.setString(1, uusi);
            statement.setInt(2, id);
            suoritaKysely();
        }
        catch (SQLException e) {
            Logger.getLogger(Osallistuja.class.getName()).log(Level.SEVERE, null, e);
        }
        
        finally {
            lopeta();
        }
    }
    
    public void poistaKilpailija(int id) {
        
        try {
            String sql = "DELETE FROM kilpailija WHERE kilpailija.id = ?";
            alustaKysely(sql);
            
            statement.setInt(1, id);
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