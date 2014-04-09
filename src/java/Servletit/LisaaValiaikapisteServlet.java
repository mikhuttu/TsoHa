package Servletit;

import Mallit.Kilpailu;
import Mallit.Valiaikapiste;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LisaaValiaikapisteServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        if (onkoKirjautunut(request) == null) {
            asetaIlmoitus("Sinun pitää ensin kirjautua sisään.", request);
            naytaJSP("kirjautuminen", request, response);
            return;
        }
        
        int kilpailuId = haeId(request);
        Kilpailu kilpailu = new Kilpailu().haeKilpailu(kilpailuId);
        
        PrintWriter out = luoPrintWriter(response);

        try {
            Valiaikapiste piste = new Valiaikapiste().haeValiaikapisteKorkeimmallaIdlla();
            Valiaikapiste piste2 = new Valiaikapiste().haeValiaikapisteKorkeimmallaNumerolla(kilpailu);
            
            int pisteId;
            int numero;
            
            try {
                pisteId = piste.getId();
            } catch (NullPointerException e) {
                pisteId = 0;
            }
                
            try {
                numero = piste2.getNumero();
            } catch(NullPointerException e) {
                numero = 0;
            }
            
            new Valiaikapiste().lisaaValiaikapisteKilpailuun(pisteId + 1, numero + 1, kilpailuId);
            
            tallennaIlmoitus("Uusi väliaikapiste lisätty kilpailuun.", request);
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
        return "Lisää väliaikapisteen kilpailuun.";
    }  


}
