package Servletit;

import Mallit.Valiaikapiste;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PoistaValiaikapisteServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {   
        response.setContentType("text/html;charset=UTF-8");
        
        if (onkoKirjautunut(request) == null) {
            asetaIlmoitus("Sinun pitää ensin kirjautua sisään.", request);
            naytaJSP("kirjautuminen", request, response);
            return;
        }
        
        int kilpailuId = haeId(request);
        
        int valiaikapisteId = haeIntArvo("valiaikapiste", request);
        
        if (valiaikapisteId == 0) {
            tallennaIlmoitus("Väliaikapistettä ei ollut valittu.", request);
            talletaSessionId(request, kilpailuId);
            
            ohjaaSivulle("kilpailu", response);
            return;
        }
        
        Valiaikapiste piste = new Valiaikapiste().haeValiaikapiste(valiaikapisteId);
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            new Valiaikapiste().poistaValiaikapiste(valiaikapisteId);
            
            tallennaIlmoitus("Väliaikapiste " + piste.getNumero() + " poistettiin kilpailusta onnistuneesti!", request);
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
        return "Poistaa kilpailusta väliaikapisteen.";
    }
}