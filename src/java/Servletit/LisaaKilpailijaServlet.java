package Servletit;

import Mallit.Kilpailija;
//import Mallit.Kilpailu;
import Mallit.Osallistuja;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LisaaKilpailijaServlet extends YleisServlet {
    
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
            new Osallistuja().lisaaOsallistuja(kilpailuId, kilpailijaId);
            asetaIlmoitus("Kilpailija " + kilpailija.getNimi() + " lisättiin kilpailuun onnistuneesti!", request);
            naytaJSP("etusivu", request, response);

//            naytaJSP("kilpailumuokkaus?id=" + kilpailuId, request, response);
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