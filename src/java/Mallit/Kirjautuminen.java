package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kirjautuminen {
    public Kayttaja etsiKayttajaTunnuksilla(String tunnus, String salasana) {
        
        Connection yhteys = null;
        PreparedStatement kysely = null;
        ResultSet tulokset = null;
        
        try {
            String sql = "SELECT tunnus, salasana FROM kayttaja WHERE tunnus = ? AND salasana = ? LIMIT 1";
            
            yhteys = Tietokanta.getYhteys();
            
            kysely = yhteys.prepareStatement(sql);
            kysely.setString(1, tunnus);
            kysely.setString(2, salasana);
            
            tulokset = kysely.executeQuery();
            
            Kayttaja kirjautunut = null;
            
            if (tulokset.next()) {
                kirjautunut = new Kayttaja(tulokset.getString("tunnus"), tulokset.getString("salasana"));
            }

            return kirjautunut;
            

        }
        
        catch (SQLException ex) {
            Logger.getLogger(Kirjautuminen.class.getName()).log(Level.SEVERE, null, ex);
            //  throw new IllegalStateException("Kayttäjää ei määritelty.");
        }
        
        finally {
            try { tulokset.close(); } catch (Exception e) {}
            try { kysely.close(); } catch (Exception e) {}
            try { yhteys.close(); } catch (Exception e) {}
        }
        
        return null;
    }
}