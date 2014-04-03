package Servletit;

import Mallit.Kayttaja;
import Mallit.Kilpailu;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
    
public class EtusivuServlet extends YleisServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        Kayttaja kirjautunut = onkoKirjautunut(request);
        request.setAttribute("kirjautunut", kirjautunut);
        
        List<Kilpailu> kilpailut = new Kilpailu().haeKilpailut();

        request.setAttribute("kilpailut", kilpailut);
        

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        
        try {
            out = response.getWriter();
        }
        catch (IOException e) {}
        
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