package Servletit.muokkaus;

import Mallit.Kilpailu;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KilpailunNimenMuokkausServlet extends YleisServlet {
    
    /**
     * Jos käyttäjä on kirjautunut, päästetään hänet muuttamaan kilpailun nimeä.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = luoPrintWriter(response);
        
        int id = haeId(request);
        
        Kilpailu kilpailu = new Kilpailu().haeKilpailu(id);
        request.setAttribute("kilpailu", kilpailu);
        
        try {
            paivitaIlmoitus(request);
            naytaJSP("muutakilpailunnimea", request, response);
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
        return "Muuttaa kannassa olevan kilpailun nimeä.";
    }
    
}
