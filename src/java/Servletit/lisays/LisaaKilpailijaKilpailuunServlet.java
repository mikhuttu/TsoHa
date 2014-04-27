package Servletit.lisays;

import Mallit.Kilpailija;
import Mallit.Osallistuja;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LisaaKilpailijaKilpailuunServlet extends YleisServlet {
    
    /**
     * Hakee kilpailun id-arvon, jonka jälkeen lisää valitun kilpailijan ko. kilpailuun.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        
        int kilpailuId = haeId(request);
        int kilpailijaId = haeIntArvo("kilpailija", request);
        String sivu = "kilpailumuokkaus";
        
        if (ohjaaSivulleJosArvoaEiValittu(request, response, kilpailijaId, kilpailuId, sivu)) {
            tallennaIlmoitus("Kilpailijaa ei ollut valittu.", request);
            return;
        }
        
        Kilpailija kilpailija = new Kilpailija().haeKilpailija(kilpailijaId);
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            new Osallistuja().lisaaOsallistuja(kilpailuId, kilpailijaId);
            
            String paivitys = "Kilpailija '" + kilpailija.getNimi() + "' lisättiin kilpailuun onnistuneesti!";
            ohjaaSivulle(paivitys, request, response, kilpailuId, sivu);
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
        return "";
    }
}