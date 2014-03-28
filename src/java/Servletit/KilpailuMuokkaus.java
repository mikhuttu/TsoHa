package Servletit;

import Mallit.Kayttaja;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KilpailuMuokkaus extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        Kayttaja kirjautunut = onkoKirjautunut(request);
        if (kirjautunut == null) {
            ohjaaSivulle("kirjautuminen", response);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        
        try {
            out = response.getWriter();
        }
        catch (IOException e) {}
        
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
        return "Short description";
    }  
}