package Mallit;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kirjautuminen extends KyselyToiminnot {
    
    public Kayttaja etsiKayttajaTunnuksilla(String tunnus, String salasana) {
        
        try {
            String sql = "SELECT tunnus, salasana FROM kayttaja WHERE tunnus = ? AND salasana = ? LIMIT 1";
            
            alustaKysely(sql);
            statement.setString(1, tunnus);
            statement.setString(2, salasana);
            
            suoritaKysely();
            
            Kayttaja kirjautunut = null;
            
            if (results.next()) {
                kirjautunut = new Kayttaja(results.getString("tunnus"), results.getString("salasana"));
            }

            return kirjautunut;
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