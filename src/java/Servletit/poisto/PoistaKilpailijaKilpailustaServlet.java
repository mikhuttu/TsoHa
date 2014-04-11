package Servletit.poisto;

import Mallit.Kilpailija;
import Mallit.Osallistuja;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PoistaKilpailijaKilpailustaServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {   
        response.setContentType("text/html;charset=UTF-8");
        
        int kilpailuId = haeId(request);
        int kilpailijaId = haeIntArvo("kilpailija", request);
        
        if (ohjaaKilpailuSivulleJosArvoaEiValittu(request, response, kilpailijaId, kilpailuId)) {
            tallennaIlmoitus("Kilpailijaa ei ollut valittu.", request);
            return;
        }
        
        Kilpailija kilpailija = new Kilpailija().haeKilpailija(kilpailijaId);
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            new Osallistuja().poistaOsallistuja(kilpailuId, kilpailijaId);
            
            String paivitys = "Kilpailija '" + kilpailija.getNimi() + "' poistettiin kilpailusta onnistuneesti!";
            ohjaaKilpailuSivulle(paivitys, request, response, kilpailuId);
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
        return "Poistaa kilpailijan kilpailusta.";
    }
}