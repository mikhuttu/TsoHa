package Servletit;

import Mallit.Kayttaja;
import Mallit.Kilpailu;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
    
public class EtusivuServlet extends YleisServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        response.setContentType("text/html;charset=UTF-8");
        
        Kayttaja kirjautunut = onkoKirjautunut(request);
        request.setAttribute("kirjautunut", kirjautunut);
        
        List<Kilpailu> kilpailut = new Kilpailu().haeKilpailut();

        request.setAttribute("kilpailut", kilpailut);

        PrintWriter out = luoPrintWriter(response);
        
        try {
            naytaJSP("etusivu", request, response);
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