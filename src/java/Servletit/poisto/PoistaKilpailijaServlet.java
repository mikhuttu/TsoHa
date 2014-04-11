package Servletit.poisto;

import Mallit.Kilpailija;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PoistaKilpailijaServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {   
        response.setContentType("text/html;charset=UTF-8");
        
        int kilpailijaId = haeId(request);
        Kilpailija kilpailija = new Kilpailija().haeKilpailija(kilpailijaId);
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            new Kilpailija().poistaKilpailija(kilpailijaId);
            
            tallennaIlmoitus("Kilpailija '" + kilpailija.getNimi() + "' poistettiin onnistuneesti!", request);
            ohjaaSivulle("etusivu", response);
        }
        
        finally {
            if (out != null) {
                out.close();
            }
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Poistaa kilpailijan tietokannasta.";
    }
}
