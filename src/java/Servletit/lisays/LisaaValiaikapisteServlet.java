package Servletit.lisays;

import Mallit.Kilpailu;
import Mallit.Valiaikapiste;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LisaaValiaikapisteServlet extends YleisServlet {
    
    /**
     * Lisää muokattavaan kilpailuun uuden väliaikapisteen.
     * Väliaikapisteen numeroksi tulee yhdellä suurempi kuin mikä on tällä hetkellä suurin
     * ko. kilpailun väliaikapisteen numero.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        
        int kilpailuId = haeId(request);
        Kilpailu kilpailu = new Kilpailu().haeKilpailu(kilpailuId);
        
        PrintWriter out = luoPrintWriter(response);

        try {
            Valiaikapiste piste = new Valiaikapiste().haeValiaikapisteKorkeimmallaNumerolla(kilpailuId);
            
            new Valiaikapiste().lisaaValiaikapisteKilpailuun(getNumero(piste), kilpailuId);
            
            String paivitys = "Uusi väliaikapiste lisätty kilpailuun.";
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
        return "";
    }  


}
