package Servletit.muokkaus;

import Mallit.Kilpailu;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MuutaKilpailunNimiServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            
            int kilpailuId = haeId(request);
            Kilpailu kilpailu = new Kilpailu().haeKilpailu(kilpailuId);

            paivitaIlmoitus(request);
            request.setAttribute("kilpailu", kilpailu);
            naytaJSP("muutakilpailunnimi", request, response);
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
        return "Lisää kantaan uuden kilpailun.";
    }
}
