package Servletit;

import Mallit.Kilpailija;
import Mallit.Osallistuja;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PoistaKilpailijaServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {   
        response.setContentType("text/html;charset=UTF-8");
        
        if (onkoKirjautunut(request) == null) {
            asetaIlmoitus("Sinun pitää ensin kirjautua sisään.", request);
            naytaJSP("kirjautuminen", request, response);
            return;
        }
        
        int kilpailijaId = haeIntArvo("kilpailija", request);
        Kilpailija kilpailija = new Kilpailija().haeKilpailija(kilpailijaId);
        
        int kilpailuId = haeId(request);
//        Kilpailu kilpailu = new Kilpailu().haeKilpailu(kilpailuId);
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            new Osallistuja().poistaOsallistuja(kilpailuId, kilpailijaId);
            
            tallennaIlmoitus("Kilpailija '" + kilpailija.getNimi() + "' poistettiin kilpailusta onnistuneesti!", request);
            talletaSessionId(request, kilpailuId);
            
            ohjaaSivulle("kilpailu", response);
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