package Servletit;

import Mallit.Kayttaja;
import Mallit.Kilpailija;
import Mallit.Kilpailu;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
    
public class EtusivuServlet extends YleisServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        response.setContentType("text/html;charset=UTF-8");
        
        Kayttaja kirjautunut = onkoKirjautunut(request);
        request.setAttribute("kirjautunut", kirjautunut);
        
        request.setAttribute("kilpailijat", new Kilpailija().haeKilpailijat());
        request.setAttribute("kilpailut", new Kilpailu().haeKilpailut());

        PrintWriter out = luoPrintWriter(response);
        
        try {
            paivitaIlmoitus(request);
            naytaJSP("etusivu", request, response);
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