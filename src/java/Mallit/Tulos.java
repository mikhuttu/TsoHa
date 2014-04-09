package Mallit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tulos extends KyselyToiminnot {
    
    private int id;
    private String aika;
    private int kilpailija;
    private int valiaikapiste;
    
    public void setId(int id) {
        this.id = id;
    }
    
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
    
    public Tulos haeTulosKorkeimmallaIdlla() {
        
        try {
            String sql = "SELECT * FROM tulos ORDER BY id desc LIMIT 1";
            alustaKysely(sql);

            suoritaKysely();
            return palautaTulos();
        }

        finally {
            lopeta();
        }
    }
    
    private Tulos palautaTulos() {
        try {
            
            if (results.next ()) {
                return palauta();
            }
            
            return null;
        }

        catch (SQLException e) {}
        
        return null;
    }
    
    private Tulos palauta() {
        
        try {
            Tulos tulos = new Tulos();
            
            tulos.setId(results.getInt("id"));
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
    
    
    public ArrayList<Tulos> haeValiaikapisteenTulokset(Valiaikapiste valiaikapiste) {
        
        try {
            
            String sql = "SELECT tulos.id, aika, kilpailija, valiaikapiste FROM tulos, kilpailija WHERE tulos.valiaikapiste = ? AND kilpailija = kilpailija.id ORDER BY aika";
            
//            String sql = "select tulos.id as tulos_id, aika, kilpailija.nimi, valiaikapiste.id as valiaika_id, numero from osallistuja "
//                    + "JOIN kilpailija ON kilpailija.id = osallistuja.kilpailija JOIN tulos on kilpailija.id = tulos.kilpailija JOIN valiaikapiste ON tulos.valiaikapiste = valiaikapiste.id  "
//                    + "where osallistuja.kilpailu = ?";

            alustaKysely(sql);
            statement.setInt(1, valiaikapiste.getId());
            
            suoritaKysely();
            
            ArrayList<Tulos> tulokset = new ArrayList<Tulos>();
            
            while (results.next()) {
                tulokset.add(palauta());
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

    public void kirjaaTulos(int tulosId, String aika, int kilpailijaId, int valiaikapisteId) {
        
        try {
            String sql = "INSERT INTO tulos VALUES (?, ?, ?, ?)";
            
            alustaKysely(sql);

            statement.setInt(1, tulosId);
            statement.setString(2, aika);
            statement.setInt(3, kilpailijaId);
            statement.setInt(4, valiaikapisteId);
            
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
