package Servletit;

import Mallit.Kilpailija;
import Mallit.Osallistuja;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LisaaKilpailijaServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        response.setContentType("text/html;charset=UTF-8");
        
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        int kilpailuId = haeId(request);
        int kilpailijaId = haeIntArvo("kilpailija", request);
        
        if (ohjaaKilpailuSivulleJosArvoaEiValittu(request, response, kilpailijaId, kilpailuId)) {
            tallennaIlmoitus("Kilpailijaa ei ollut valittu.", request);
            return;
        }
        
        Kilpailija kilpailija = new Kilpailija().haeKilpailija(kilpailijaId);
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            new Osallistuja().lisaaOsallistuja(kilpailuId, kilpailijaId);
            
            String paivitys = "Kilpailija '" + kilpailija.getNimi() + "' lisättiin kilpailuun onnistuneesti!";
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
        return "Vie käyttäjän etusivulle.";
    }
}