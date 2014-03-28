package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KirjautuminenMalli {
    public static Kayttaja etsiKayttajaTunnuksilla(String tunnus, String salasana) {
        
        try {
            String sql = "SELECT tunnus, salasana FROM kayttaja WHERE tunnus = ? AND salasana = ?";
            
            Connection yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(sql);
            kysely.setString(1, tunnus);
            kysely.setString(2, salasana);
            ResultSet tulokset = kysely.executeQuery();
            
            Kayttaja kirjautunut = null;
            
            if (tulokset.next()) {
                kirjautunut = new Kayttaja(tulokset.getString("tunnus"), tulokset.getString("salasana"));
            }
            
            try { tulokset.close(); } catch (SQLException e) {}
            try { kysely.close(); } catch (SQLException e) {}
            try { yhteys.close(); } catch (SQLException e) {}
            
            return kirjautunut;
        }
        
        catch (SQLException ex) {
            Logger.getLogger(KirjautuminenMalli.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("Kayttäjää ei määritelty.");
        }
    }
}