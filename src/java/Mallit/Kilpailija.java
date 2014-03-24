package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Kilpailija {
    private final int id;
    private final String nimi;
    
    public Kilpailija(int ID, String Nimi) {
        this.id = ID;
        this.nimi = Nimi;
    }
    
    public int getID() {
        return this.id;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public static List<Kilpailija> getKilpailijat() throws Exception {
        Tietokanta tietokanta = new Tietokanta();
        
        String sql = "SELECT ID, Nimi from kilpailija";
        Connection yhteys = tietokanta.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();

        ArrayList<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
        while (tulokset.next()) {
            Kilpailija k = new Kilpailija(tulokset.getInt("ID"), tulokset.getString("Nimi"));
            kilpailijat.add(k);
        }   

        try { tulokset.close(); } catch (SQLException e) {}
        try { kysely.close(); } catch (SQLException e) {}
        try { yhteys.close(); } catch (SQLException e) {}

        return kilpailijat;
    }
}
