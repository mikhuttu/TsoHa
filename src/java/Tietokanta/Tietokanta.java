package Tietokanta;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Tietokanta {
    private InitialContext cxt;
    private DataSource yhteysVarasto;
    
    public Tietokanta() throws Exception {
        cxt = new InitialContext();
        yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");
    }
    
    public Connection getYhteys() throws SQLException {
        return yhteysVarasto.getConnection();
    }
}
