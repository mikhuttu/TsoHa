package Mallit;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

    /**
     * Osallistuja on välitaulu, joka linkittää kilpailut ja kilpailijat toisiinsa.
    */

public class Osallistuja extends KyselyToiminnot {
    
    public void lisaaOsallistuja(int kilpailuId, int kilpailijaId) {
        try {
            String sql = "INSERT INTO osallistuja (kilpailu, kilpailija) VALUES (?, ?)";
            
            alustaKysely(sql);

            statement.setInt(1, kilpailuId);
            statement.setInt(2, kilpailijaId);
            
            suoritaKysely();
        }
        
        catch (SQLException e) {
            Logger.getLogger(Osallistuja.class.getName()).log(Level.SEVERE, null, e);
        }
        
        finally {
            lopeta();
        }
    }

    public void poistaOsallistuja(int kilpailuId, int kilpailijaId) {
        try {
            String sql = "DELETE FROM osallistuja WHERE osallistuja.kilpailu = ? AND osallistuja.kilpailija = ?";
            
            alustaKysely(sql);

            statement.setInt(1, kilpailuId);
            statement.setInt(2, kilpailijaId);
            
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