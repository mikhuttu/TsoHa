package Mallit;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* Palauttaa käyttäjän jonka tunnus on "tunnus" ja salasana "salasana", jos sellainen kayttaja-taulussa on.
*/

public class Kayttaja extends KyselyToiminnot {
    private String tunnus;
    private String salasana;
    
    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }
    
    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }
    
    public Kayttaja etsiKayttaja(String tunnus, String salasana) {
        
        try {
            String sql = "SELECT tunnus, salasana FROM kayttaja WHERE tunnus = ? AND salasana = ? LIMIT 1";
            
            alustaKysely(sql);
            statement.setString(1, tunnus);
            statement.setString(2, salasana);
            
            suoritaKysely();
            
            Kayttaja kirjautunut = null;
            
            if (results.next()) {
                kirjautunut = new Kayttaja();
                kirjautunut.setTunnus(tunnus);
                kirjautunut.setSalasana(salasana);
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