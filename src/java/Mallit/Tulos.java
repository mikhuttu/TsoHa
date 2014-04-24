package Mallit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Malli kuvaa kilpailijoiden tuloksia tietyillä väliaikapisteillä.
 */

public class Tulos extends KyselyToiminnot {
    
    private int id;
    private String aika;
    private int kilpailija;
    private int valiaikapiste;
    
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Ajanmääritys tapahtuu näin sillä siihen halutaan kaksoispisteet tuntien ja minuuttien sekä
     * minuuttien ja sekuntien väliin.
     * @param aika 
     */
    
    public void setAika(String aika) {
        this.aika = "";
        
        int i = 0;
        
        while (i < aika.length()) {
            this.aika += aika.charAt(i);
            
            if (i % 2 != 0 && i < aika.length() - 1) {
                this.aika += ":";
            }
            
            i++;
        }
    }
    
    public void setKilpailija(int kilpailija) {
        this.kilpailija = kilpailija;
    }
    
    public void setValiaikapiste(int valiaikapiste) {
        this.valiaikapiste = valiaikapiste;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getAika() {
        return this.aika;
    }
    
    public int getKilpailija() {
        return this.kilpailija;
    }
    
    public int getValiaikapiste() {
        return this.valiaikapiste;
    }
    
    /**
     * Joissain tilanteissa näkymien puolella on mielekästä kysyä tulokselta kilpailija, jota tulos koskee.
     * Sillä tulos sisältää ainoastaan tiedon kilpailijan id:stä, tällä metodilla saadaa ko. kilpailijan
     * nimi helposti selville.
     */
    
    public String getKilpailijaNimi() {
        
        try {
            String sql = "SELECT * FROM kilpailija WHERE kilpailijaId = ? LIMIT 1";
            alustaKysely(sql);
            
            statement.setInt(1, this.kilpailija);
            suoritaKysely();
            
            if (results.next()) {
                return results.getString("nimi");
            }
            return null;
        }
        catch (SQLException e) {}
        
        finally {
            lopeta();
        }
        
        return null;
    }
    
    /**
     * Palauttaa "results":sta seuraavan tuloksen.
     */
    
    private Tulos palautaTulos() {
        
        try {
            Tulos tulos = new Tulos();
            
            tulos.setId(results.getInt("tulosId"));
            tulos.setAika(results.getString("aika"));
            tulos.setKilpailija(results.getInt("kilpailija"));
            tulos.setValiaikapiste(results.getInt("valiaikapiste"));
            
            return tulos;
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(Valiaikapiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Hakee kaikki tulokset, jotka koskevat tietttyä väliaikapistettä.
     * @param valiaikapiste
     */
    
    public ArrayList<Tulos> haeValiaikapisteenTulokset(int valiaikapisteId) {
        
        try {
            
            String sql = "SELECT tulosId, aika, kilpailija, valiaikapiste FROM tulos, kilpailija WHERE tulos.valiaikapiste = ? AND kilpailija = kilpailijaId ORDER BY aika";

            alustaKysely(sql);
            statement.setInt(1, valiaikapisteId);
            
            suoritaKysely();
            
            ArrayList<Tulos> tulokset = new ArrayList<Tulos>();
            
            while (results.next()) {
                tulokset.add(palautaTulos());
            }
            
            return tulokset;
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
     * Tuloksen kirjaaminen tapahtuu siten että tarkistetaan, onko ko. kilpailijalla olemassa jo tulos
     * ko. väliaikapisteellä. Jos ei ole, lisätään uusi tulos ja jos on, päivitetään vanhaa.
     * @param aika
     * @param kilpailijaId
     * @param valiaikapisteId 
     */
    
    public void kirjaaTulos(String aika, int kilpailijaId, int valiaikapisteId) {
        
        try {
            int tulosId = paivitetaankoVanhaTulos(kilpailijaId, valiaikapisteId);
            
            if (tulosId != 0) {
                paivitaVanhaTulos(tulosId, aika, kilpailijaId, valiaikapisteId);
            }

            else {
                kirjaaUusiTulos(aika, kilpailijaId, valiaikapisteId);
            }
        }
        finally {
            lopeta();
        }
    }
   
    private int paivitetaankoVanhaTulos(int kilpailijaId, int valiaikapisteId) {
        
        try {
            String sql = "SELECT * FROM tulos WHERE kilpailija = ? AND valiaikapiste = ? LIMIT 1";
            alustaKysely(sql);

            statement.setInt(1, kilpailijaId);
            statement.setInt(2, valiaikapisteId);
            suoritaKysely();
            
            if (results.next()) {
                return results.getInt("tulosId");
            }
        }
        catch (SQLException e) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    
    private void paivitaVanhaTulos(int tulosId, String aika, int kilpailijaId, int valiaikapisteId) {
        try {
            String sql = "UPDATE tulos SET aika = ?, kilpailija = ?, valiaikapiste = ? WHERE tulosId = ?";
            
            alustaKysely(sql);

            statement.setString(1, aika);
            statement.setInt(2, kilpailijaId);
            statement.setInt(3, valiaikapisteId);
            statement.setInt(4, tulosId);
            
            suoritaKysely();
        }
        
        catch (SQLException e) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void kirjaaUusiTulos(String aika, int kilpailijaId, int valiaikapisteId) {
        
        try {
            String sql = "INSERT INTO tulos (aika, kilpailija, valiaikapiste) VALUES (?, ?, ?)";
            
            alustaKysely(sql);

            statement.setString(1, aika);
            statement.setInt(2, kilpailijaId);
            statement.setInt(3, valiaikapisteId);
            
            suoritaKysely();
        }
        
        catch (SQLException e) {
            Logger.getLogger(Osallistuja.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void poistaKilpailijanTulokset(int kilpailuId, int kilpailijaId) {
        
        try {
            
            String sql = "DELETE FROM tulos WHERE tulosId IN "
                    + "(SELECT t.tulosId FROM tulos t "
                    + "JOIN kilpailija k ON t.kilpailija = k.kilpailijaId "
                    + "JOIN valiaikapiste v ON t.valiaikapiste = v.valiaikapisteId "
                    + "WHERE t.kilpailija = ? AND v.kilpailu = ?)";
            
            alustaKysely(sql);

            statement.setInt(1, kilpailijaId);
            statement.setInt(2, kilpailuId);
            
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
