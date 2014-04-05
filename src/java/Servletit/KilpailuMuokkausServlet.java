package Servletit;

import Mallit.Kayttaja;
import Mallit.Kilpailu;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KilpailuMuokkausServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = luoPrintWriter(response);
        
        Kayttaja kirjautunut = onkoKirjautunut(request);
        
        if (kirjautunut == null) {
            asetaIlmoitus("Sinun pitää ensin kirjautua sisään.", request);
            naytaJSP("kirjautuminen", request, response);
            return;
        }
        
        int id = haeId(request);
        Kilpailu kilpailu = new Kilpailu().haeKilpailu(id);
        
        request.setAttribute("kilpailu", kilpailu);
        request.setAttribute("osallistujat", kilpailu.haeOsallistujat());
        request.setAttribute("muutKilpailijat", kilpailu.haeKilpailijatJotkaEivatOsallistu());
        request.setAttribute("valiaikapisteet", kilpailu.haeValiaikapisteet());
        
        try {
            naytaJSP("kilpailumuokkaus", request, response);
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
        return "Vie käyttäjän kilpailun 'id' muokkaussivulle.";
    }  
}