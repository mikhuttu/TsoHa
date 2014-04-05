package Mallit;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Osallistuja extends KyselyToiminnot {

    public void lisaaOsallistuja(int kilpailuId, int kilpailijaId) {
        try {
            String sql = "INSERT INTO osallistuja VALUES (?, ?)";
            
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