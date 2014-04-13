package Mallit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Malli kuvaa väliaikapistettä, joka liittyy yhteen kilpailuun.
 */

public class Valiaikapiste extends KyselyToiminnot {
    private int id;
    private int numero;
    private int kilpailu;

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
    
    public ArrayList<Valiaikapiste> haeKilpailunValiaikapisteet(Kilpailu kilpailu) {
        
        try {
            String sql = "SELECT * FROM valiaikapiste WHERE valiaikapiste.kilpailu = ? ORDER BY valiaikapiste.numero";
            alustaKysely(sql);
            
            statement.setInt(1, kilpailu.getId());
            suoritaKysely();
            return palautaValiaikapisteet();
        }

        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        return null;
    }
    
    /**
     * Hakee kilpailun väliaikapisteen, jolla on korkein numero.
     * Ts. kyseinen väliaikapiste on "maali", ja sitä varten halutaan hakea kilpailun lopputuloksia.
     * @param kilpailu
     */
    
    public Valiaikapiste haeValiaikapisteKorkeimmallaNumerolla(Kilpailu kilpailu) {
        
        try {
            String sql = "SELECT * FROM valiaikapiste WHERE valiaikapiste.kilpailu = ? ORDER BY numero desc LIMIT 1";
            alustaKysely(sql);

            statement.setInt(1, kilpailu.getId());
            suoritaKysely();
            
            if (results.next()) {
                return palauta();
            }
        }
        
        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally {
            lopeta();
        }
        return null;
    }
    
    public Valiaikapiste haeValiaikapiste (int valiaikapisteId) {
        try {
            
            String sql = "SELECT * FROM valiaikapiste WHERE valiaikapiste.id = ? LIMIT 1";
            alustaKysely(sql);
            
            statement.setInt(1, valiaikapisteId);
            suoritaKysely();
            
            if (results.next()) {
                return palauta();
            }
        }
        
        catch (SQLException ex) {
            Logger.getLogger(Valiaikapiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }

    public void lisaaValiaikapisteKilpailuun(int valiaikaPisteita, int kilpailuId) {
        try {
            String sql = "INSERT INTO valiaikapiste (numero, kilpailu) VALUES (?, ?)";
            alustaKysely(sql);
            
            statement.setInt(1, valiaikaPisteita + 1);
            statement.setInt(2, kilpailuId);
            suoritaKysely();
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(Valiaikapiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
    }

    public void poistaValiaikapiste(int valiaikapisteId) {
        try {
            String sql = "DELETE FROM valiaikapiste WHERE valiaikapiste.id = ?";
            
            alustaKysely(sql);

            statement.setInt(1, valiaikapisteId);
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
     * Palauttaa listan haetuista väliaikapisteistä.
     */
    
    private ArrayList<Valiaikapiste> palautaValiaikapisteet() {
        
        try {
            ArrayList<Valiaikapiste> pisteet = new ArrayList<Valiaikapiste>();
            
            while (results.next()) {
                pisteet.add(palauta());
            }
            return pisteet;
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(Valiaikapiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Palauttaa "results":ssa seuraavana olevan väliaikapisteen.
     */
    
    private Valiaikapiste palauta() {
        
        try {
            Valiaikapiste piste = new Valiaikapiste();
            
            piste.setId(results.getInt("id"));
            piste.setNumero(results.getInt("numero"));
            piste.setKilpailu(results.getInt("kilpailu"));
            
            return piste;
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(Valiaikapiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}