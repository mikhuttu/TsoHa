package Servletit;

import Mallit.Kilpailu;
import Mallit.Valiaikapiste;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LisaaValiaikapisteServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        int kilpailuId = haeId(request);
        Kilpailu kilpailu = new Kilpailu().haeKilpailu(kilpailuId);
        
        PrintWriter out = luoPrintWriter(response);

        try {
            Valiaikapiste piste = new Valiaikapiste().haeValiaikapisteKorkeimmallaIdlla();
            Valiaikapiste piste2 = new Valiaikapiste().haeValiaikapisteKorkeimmallaNumerolla(kilpailu);
            
            new Valiaikapiste().lisaaValiaikapisteKilpailuun(getId(piste) + 1, getNumero(piste) + 1, kilpailuId);
            
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
        return "Lisää väliaikapisteen kilpailuun.";
    }  


}
