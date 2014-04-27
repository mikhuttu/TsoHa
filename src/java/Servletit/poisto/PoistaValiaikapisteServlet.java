package Servletit.poisto;

import Mallit.Valiaikapiste;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PoistaValiaikapisteServlet extends YleisServlet {
    
    /**
     * Poistaa kilpailusta väliaikapisteen mikäli se on valittu.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {   
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        
        int kilpailuId = haeId(request);
        int valiaikapisteId = haeIntArvo("valiaikapiste", request);
        String sivu = "kilpailumuokkaus";
        
        if (ohjaaSivulleJosArvoaEiValittu(request, response, valiaikapisteId, kilpailuId, sivu)) {
            tallennaIlmoitus("Väliaikapistettä ei ollut valittu.", request);
            return;
        }
        
        Valiaikapiste piste = new Valiaikapiste().haeValiaikapiste(valiaikapisteId);
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            new Valiaikapiste().poistaValiaikapiste(valiaikapisteId);
            
            String paivitys = "Väliaikapiste " + piste.getNumero() + " poistettiin kilpailusta onnistuneesti!";
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