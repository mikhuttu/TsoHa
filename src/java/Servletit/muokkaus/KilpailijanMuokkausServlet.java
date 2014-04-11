package Servletit.muokkaus;

import Mallit.Kilpailija;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KilpailijanMuokkausServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = luoPrintWriter(response);
        
        int id = haeId(request);
        
        Kilpailija kilpailija = new Kilpailija().haeKilpailija(id);
        request.setAttribute("kilpailija", kilpailija);
        
        try {
            paivitaIlmoitus(request);
            naytaJSP("kilpailijanmuokkaus", request, response);
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
        return "Muuttaa kannassa olevan kilpailijan nimeä.";
    }
    
}