
package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tulos {
    
    private int id;
    private String aika;
    private String kilpailija;
    private int valiaikapiste;
    private int valiaikapistenumero;
    
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
    
    public void setKilpailija(String kilpailija) {
        this.kilpailija = kilpailija;
    }
    
    public void setValiaikapiste(int valiaikapiste) {
        this.valiaikapiste = valiaikapiste;
    }
    
    public void setValiaikapistenumero(int valiaikapistenumero) {
        this.valiaikapistenumero = valiaikapistenumero;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getAika() {
        return this.aika;
    }
    
    public String getKilpailija() {
        return this.kilpailija;
    }
    
    public int getValiaikapiste() {
        return this.valiaikapiste;
    }
    
    public int getValiaikapistenumero() {
        return this.valiaikapistenumero;
    }
    
    
    public ArrayList<Tulos> haeValiaikapisteenTulokset(Valiaikapiste valiaikapiste) {
        
        Connection yhteys = null;
        PreparedStatement kysely = null;
        ResultSet tulokset = null;
        
        try {
            
            String sql = "SELECT DISTINCT tulos.id, tulos.aika, kilpailija.nimi, valiaikapiste.id as valiaika_id, valiaikapiste.numero "
                    + "FROM tulos, kilpailija, valiaikapiste "
                     + "WHERE tulos.kilpailija = kilpailija.id AND      AND tulos.valiaikapiste = ? ORDER BY tulos.aika";
            
            
//            sql = "select tulos.id, tulos.aika, kilpailu.nimi, valiaikapiste.id as valiaika_id, valiaikapiste.numero"
//                    + " from osallistuja JOIN kilpailija on kilpailija.id = osallistuja.kilpailija "
//                    + "JOIN tulos on kilpailija.id = tulos.kilpailija  "
//                    + "where osallistuja.kilpailu = ?";
            
            sql = "select tulos.id as tulos_id, aika, kilpailija.nimi, valiaikapiste.id as valiaika_id, numero from osallistuja "
                    + "JOIN kilpailija ON kilpailija.id = osallistuja.kilpailija JOIN tulos on kilpailija.id = tulos.kilpailija JOIN valiaikapiste ON tulos.valiaikapiste = valiaikapiste.id  "
                    + "where osallistuja.kilpailu = ?";
            
//            sql = "select tulos.id as tulos_id ,aika, nimi, numero "
//                    + "from osallistuja JOIN kilpailija ON kilpailija.id = osallistuja.kilpailija JOIN tulos on kilpailija.id = tulos.kilpailija  "
//                    + "where osallistuja.kilpailu = ?";
            
            

            yhteys = Tietokanta.getYhteys();
            kysely = yhteys.prepareStatement(sql);
            
            kysely.setInt(1, valiaikapiste.getId());
            
            tulokset = kysely.executeQuery();
            
            ArrayList<Tulos> tuloslista = new ArrayList<Tulos>();
            
            while (tulokset.next()) {

                Tulos tulos = new Tulos();
                
                tulos.setId(tulokset.getInt("tulos_id"));
                tulos.setAika(tulokset.getString("aika"));
                tulos.setKilpailija(tulokset.getString("nimi"));
                tulos.setValiaikapiste(tulokset.getInt("valiaika_id"));
                tulos.setValiaikapistenumero(tulokset.getInt("numero"));

                tuloslista.add(tulos);
            }
            
            return tuloslista;
            
        } catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            try { tulokset.close(); } catch (Exception e) {}
            try { kysely.close(); } catch (Exception e) {}
            try { yhteys.close(); } catch (Exception e) {}
        }
        
        return null;
    }
}
