package Mallit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Malli joka sisältää tietoa kilpailuista sekä tekee niiden hakuun liittyviä SQL-kyselyjä.
 */

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
    
    /**
    * Hakee kaikki kilpailijat nimen mukaan aakkosjärjestyksessä.
    */
    
    public List<Kilpailu> haeKilpailut() {

       try {
           String sql = "SELECT * FROM kilpailu ORDER BY nimi";
           alustaKysely(sql);
            
           suoritaKysely();
        
           return palautaKaikki();
       }

       finally {
          lopeta();
       }
    }
    
    public Kilpailu haeKilpailu(int kilpailuId) {

        try {
            String sql = "SELECT * FROM kilpailu WHERE kilpailuId = ? LIMIT 1";

            alustaKysely(sql);
            statement.setInt(1, kilpailuId);
            
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
    
    /**
    * Hakee kilpailun nimen perusteella. Kaikilla kilpailuilla on myös identifioiva nimi.
    */
    
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
    
    
    /**
     * Palauttaa kilpailun, joka sisältää ko. väliaikapisteen.
     * @param valiaikapisteId
     * @return 
     */
    
    public Kilpailu haeKilpailuJossaValiaikapiste(int valiaikapisteId) {
        
        try {
            String sql = "SELECT kilpailuId, nimi FROM kilpailu, valiaikapiste WHERE "
                    + "kilpailuId = valiaikapiste.kilpailu AND valiaikapisteId = ? LIMIT 1";

            alustaKysely(sql);
            statement.setInt(1, valiaikapisteId);
            
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
    
    /**
    * Palauttaa kaikki tietokannasta haetut kilpailut.
    */    
    
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
    
    /**
    * Palauttaa seuraavana "results":ssa olevan kilpailun.
    */
    
    private Kilpailu palauta() {
        Kilpailu kilpailu = null;
        
        try {
            kilpailu = new Kilpailu();
            
            kilpailu.setId(results.getInt("kilpailuId"));
            kilpailu.setNimi(results.getString("nimi"));
        }
        
        catch (SQLException ex) {
            Logger.getLogger(Valiaikapiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kilpailu;
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
            String sql = "UPDATE kilpailu SET nimi = ? WHERE kilpailuId = ?";
            alustaKysely(sql);

            statement.setString(1, nimi);
            statement.setInt(2, id);
            
            suoritaKysely();
        }
        
        catch (SQLException e) {
            Logger.getLogger(Osallistuja.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void poistaKilpailu(int id) {
        
        try {
            String sql = "DELETE FROM kilpailu WHERE kilpailuId = ?";
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

    /**
     * Kilpailun lisäys tapahtuu siten että ensin kysytään, onko samannimistä kilpailua jo olemassa.
     * Jos ei ole, voidaan kilpailu lisätä.
     * @param nimi = lisättävän kilpailun nimi
     */
    
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
    
    /**
     * Metodi selvittää onko samannimistä kilpailua jo olemassa.
     * @param nimi
     */
    
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
    
    /**
     * Palauttaa kaikki kilpailut, jossa parametrina määritelty kilpailija kilpailee.
     * @param kilpailijaId = kilpailijan id
     */
    
    public ArrayList<Kilpailu> haeKilpailut(int kilpailijaId) {
        try {
            String sql = "SELECT * FROM kilpailu, osallistuja WHERE kilpailuId = osallistuja.kilpailu AND osallistuja.kilpailija = ?";
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