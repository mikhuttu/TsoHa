package Mallit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kilpailu extends KyselyToiminnot {
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
    
    public void setValiaikapisteet() {
        this.valiaikapisteet = new Valiaikapiste().haeKilpailunValiaikapisteet(this);
    }
    
    public List<Kilpailija> haeOsallistujat() {
        return new Kilpailija().haeKilpailunKilpailijat(this);
    }
    
    public List<Kilpailija> haeKilpailijatJotkaEivatOsallistu() {
       return new Kilpailija().haeKaikkiJotkaEivatOsallistu(this);
    }
    
    public List<Valiaikapiste> haeValiaikapisteet() {
        return new Valiaikapiste().haeKilpailunValiaikapisteet(this);
    }
    
    public List<Tulos> haeTulokset(int valiaikapiste) {
        if (valiaikapisteet == null) {
            setValiaikapisteet();
        }

        return valiaikapisteet.get(valiaikapiste).haeTulokset();
    }
    
    public List<Tulos> haeLoppuTulokset() {
        if (valiaikapisteet == null) {
            setValiaikapisteet();
        }
        
        if (valiaikapisteet.isEmpty()) {
            return null;
        }
        return haeTulokset(valiaikapisteet.size() - 1);
    }
    
    public void lisaaValiaikapiste(Valiaikapiste valiaikapiste) {

    }
    
    
    public List<Kilpailu> haeKilpailut() {

        try {
            String sql = "SELECT id, nimi FROM kilpailu";
            
            alustaKysely(sql);
            suoritaKysely();
            
            ArrayList<Kilpailu> kilpailut = new ArrayList<Kilpailu>();
            
            while (results.next()) {
                
                Kilpailu k = new Kilpailu();
                k.setId(results.getInt("id"));
                k.setNimi(results.getString("nimi"));
                k.setKilpailijat();
                
                kilpailut.add(k);
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
            
            Kilpailu kilpailu = null;
            
            if (results.next()) {
                kilpailu = new Kilpailu();
                
                kilpailu.setId(results.getInt("id"));
                kilpailu.setNimi(results.getString("nimi"));
                kilpailu.setKilpailijat();
            }
            
            return kilpailu;
            
        } catch (SQLException ex) {
            Logger.getLogger(Kilpailu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
}