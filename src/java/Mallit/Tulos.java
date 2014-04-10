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
    
    public String getKilpailijaNimi() {
        try {
            String sql = "SELECT * FROM kilpailija WHERE kilpailija.id = ? LIMIT 1";
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
    
    private Tulos palautaTulos() {
        
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
                return results.getInt("id");
            }
        }
        catch (SQLException e) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return 0;
    }
    
    private void paivitaVanhaTulos(int tulosId, String aika, int kilpailijaId, int valiaikapisteId) {
        try {
            String sql = "UPDATE tulos SET aika = ?, kilpailija = ?, valiaikapiste = ? WHERE id = ?";
            
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
}
