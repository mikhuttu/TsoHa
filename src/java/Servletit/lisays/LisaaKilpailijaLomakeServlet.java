package Servletit.lisays;

import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LisaaKilpailijaLomakeServlet extends YleisServlet {

    /**
     * Näyttää kilpailijanlisäyslomakkeen jos käyttäjä on kirjautunut sisään.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {   
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = luoPrintWriter(response);
        
        try {
            paivitaIlmoitus(request);
            naytaJSP("lisaakilpailijalomake", request, response);
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
