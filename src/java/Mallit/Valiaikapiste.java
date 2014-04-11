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
        return this.getValue(this.id);
    }
    
    public int getKilpailu() {
        return this.getValue(this.kilpailu);
    }
    
    public int getNumero() {
        return this.getValue(this.numero);
    }
    
    public int getValue(int value) {
        if (this == null) {
            return 0;
        }
        return value;
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
            
            if (results.next()) {
                return palautaValiaikaPiste();
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
    
    public ArrayList<Valiaikapiste> haeKilpailunValiaikapisteet(Kilpailu kilpailu) {
        
        try {
            String sql = "SELECT * FROM valiaikapiste WHERE valiaikapiste.kilpailu = ? ORDER BY valiaikapiste.numero";
            alustaKysely(sql);
            
            statement.setInt(1, kilpailu.getId());
            suoritaKysely();
            return palautaListaValiaikapisteista();
        }

        catch (SQLException ex) {
            Logger.getLogger(Tulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            lopeta();
        }
        return null;
    }
    
    public Valiaikapiste haeValiaikapisteKorkeimmallaNumerolla(Kilpailu kilpailu) {
        
        try {
            String sql = "SELECT * FROM valiaikapiste WHERE valiaikapiste.kilpailu = ? ORDER BY numero desc LIMIT 1";
            alustaKysely(sql);

            statement.setInt(1, kilpailu.getId());
            suoritaKysely();
            
            if (results.next()) {
                return palautaValiaikaPiste();
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
                return palautaValiaikaPiste();
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
    
    private ArrayList<Valiaikapiste> palautaListaValiaikapisteista() {
        try {
            ArrayList<Valiaikapiste> pisteet = new ArrayList<Valiaikapiste>();
            
            while (results.next()) {
                pisteet.add(palautaValiaikaPiste());
            }
            return pisteet;
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(Valiaikapiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private Valiaikapiste palautaValiaikaPiste() {
        
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