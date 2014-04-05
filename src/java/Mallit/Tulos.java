package Mallit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tulos extends KyselyToiminnot {
    
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
        
        try {
            String sql = "select tulos.id as tulos_id, aika, kilpailija.nimi, valiaikapiste.id as valiaika_id, numero from osallistuja "
                    + "JOIN kilpailija ON kilpailija.id = osallistuja.kilpailija JOIN tulos on kilpailija.id = tulos.kilpailija JOIN valiaikapiste ON tulos.valiaikapiste = valiaikapiste.id  "
                    + "where osallistuja.kilpailu = ?";

            alustaKysely(sql);
            statement.setInt(1, valiaikapiste.getId());
            
            suoritaKysely();
            
            ArrayList<Tulos> tulokset = new ArrayList<Tulos>();
            
            while (results.next()) {

                Tulos tulos = new Tulos();
                
                tulos.setId(results.getInt("tulos_id"));
                tulos.setAika(results.getString("aika"));
                tulos.setKilpailija(results.getString("nimi"));
                tulos.setValiaikapiste(results.getInt("valiaika_id"));
                tulos.setValiaikapistenumero(results.getInt("numero"));

                tulokset.add(tulos);
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

}
