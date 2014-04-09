package Servletit;

import Mallit.Valiaikapiste;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PoistaValiaikapisteServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {   
        response.setContentType("text/html;charset=UTF-8");
        
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        int kilpailuId = haeId(request);
        
        int valiaikapisteId = haeIntArvo("valiaikapiste", request);
        
        if (ohjaaKilpailuSivulleJosArvoaEiValittu(request, response, valiaikapisteId, kilpailuId)) {
            tallennaIlmoitus("Väliaikapistettä ei ollut valittu.", request);
            return;
        }
        
        Valiaikapiste piste = new Valiaikapiste().haeValiaikapiste(valiaikapisteId);
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            new Valiaikapiste().poistaValiaikapiste(valiaikapisteId);
            
            String paivitys = "Väliaikapiste " + piste.getNumero() + " poistettiin kilpailusta onnistuneesti!";
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
        return "Poistaa kilpailusta väliaikapisteen.";
    }
}