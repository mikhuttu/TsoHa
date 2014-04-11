package Servletit;

import Mallit.Kilpailija;
import Mallit.Kilpailu;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
    
public class KilpailijaServlet extends YleisServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        response.setContentType("text/html;charset=UTF-8");
        
        int kilpailijaId = haeIntArvo("kilpailija", request);
        
        if (kilpailijaId == 0) {
            kilpailijaId = haeId(request);
        }

        
        request.setAttribute("kilpailija", new Kilpailija().haeKilpailija(kilpailijaId));
        request.setAttribute("kilpailut", new Kilpailu().haeKilpailut(kilpailijaId));
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            paivitaIlmoitus(request);
            naytaJSP("kilpailija", request, response);
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
        return "Näyttää kilpailijanpoistosivun.";
    }
}