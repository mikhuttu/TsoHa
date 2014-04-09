package Servletit;

import Mallit.Kilpailija;
import Mallit.Kilpailu;
import Mallit.Valiaikapiste;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KilpailuMuokkausServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = luoPrintWriter(response);
        
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        int id = haeId(request);
        Kilpailu kilpailu = new Kilpailu().haeKilpailu(id);
        
        request.setAttribute("kilpailu", kilpailu);
        request.setAttribute("osallistujat",new Kilpailija().haeKilpailunKilpailijat(kilpailu));
        request.setAttribute("muutKilpailijat", new Kilpailija().haeKaikkiJotkaEivatOsallistu(kilpailu));
        request.setAttribute("valiaikapisteet", new Valiaikapiste().haeKilpailunValiaikapisteet(kilpailu));
        
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