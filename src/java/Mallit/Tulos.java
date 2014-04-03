package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tulos implements Comparable<Tulos> {
    private int id;
    private String aika;
    private int kilpailija;
    private int valiaikapiste;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setAika(String aika) {
        this.aika = aika;
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
    
    
    public ArrayList<Tulos> haeValiaikapisteenTulokset(Valiaikapiste valiaikapiste) {
        
        try {
            String sql = "SELECT id, aika, kilpailija, valiaikapiste FROM tulos WHERE valiaikapiste = ?";
            Connection yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(sql);
            
            Integer idParam = valiaikapiste.getId();
            
            kysely.setString(1, idParam.toString());
            
            ResultSet tulokset = kysely.executeQuery();
            
            ArrayList<Tulos> tuloslista = new ArrayList<Tulos>();
            
            while (tulokset.next()) {

                Tulos tulos = new Tulos();
                
                tulos.setId(tulokset.getInt("id"));
                tulos.setAika(tulokset.getString("aika"));
                tulos.setKilpailija(tulokset.getInt("kilpailija"));
                tulos.setValiaikapiste(tulokset.getInt("valiaikapiste"));

                tuloslista.add(tulos);
            }
            
            try { tulokset.close(); } catch (SQLException e) {}
            try { kysely.close(); } catch (SQLException e) {}
            try { yhteys.close(); } catch (SQLException e) {}
            
            return tuloslista;
            
        } catch (SQLException ex) {
            Logger.getLogger(Kilpailija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int compareTo(Tulos toinen) {
        return this.aika.compareTo(toinen.aika);
    }
}
