package Mallit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public ArrayList<Tulos> haeTulokset() {
        return new Tulos().haeValiaikapisteenTulokset(this);
    }
    
    public Valiaikapiste haeValiaikapisteTulosTaulunKautta() {

        try {
            String sql = "SELECT id, kilpailu FROM valiaikapiste WHERE valiaikapiste.id = ? LIMIT 1";
            
            alustaKysely(sql);
            statement.setString(1, "tulos.valiaikapiste");

            suoritaKysely();
            
            Valiaikapiste piste = null;
            
            if (results.next()) {
                piste = new Valiaikapiste();
                piste.setId(results.getInt("id"));
                piste.setKilpailu(results.getInt("kilpailu"));
            }
            
            return piste;
        }
        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
    
    public ArrayList<Valiaikapiste> haeKilpailunValiaikapisteet(Kilpailu kilpailu) {
        
        try {
            String sql = "SELECT DISTINCT id, numero, kilpailu FROM valiaikapiste WHERE valiaikapiste.kilpailu = ?";
            
            alustaKysely(sql);
            statement.setInt(1, kilpailu.getId());

            suoritaKysely();
            
            ArrayList<Valiaikapiste> valiaikapisteet = new ArrayList<Valiaikapiste>();
            
            while (results.next()) {
                
                Valiaikapiste piste = new Valiaikapiste();
                piste.setId(results.getInt("id"));
                piste.setNumero(results.getInt("numero"));
                piste.setKilpailu(results.getInt("kilpailu"));
                
                valiaikapisteet.add(piste);
            }
            
            return valiaikapisteet;
        }
        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        
        return null;
    }
}