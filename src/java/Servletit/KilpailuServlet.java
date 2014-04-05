package Servletit;

import Mallit.Kilpailu;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
    
public class KilpailuServlet extends YleisServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        response.setContentType("text/html;charset=UTF-8");

        int id = haeId(request);
        
        Kilpailu kilpailu = new Kilpailu().haeKilpailu(id);
        request.setAttribute("kilpailu", kilpailu);
        request.setAttribute("kilpailijat", kilpailu.haeOsallistujat());
        
        request.setAttribute("kilpailutulokset", kilpailu.haeLoppuTulokset());
        request.setAttribute("valiaikapisteet", kilpailu.haeValiaikapisteet());
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            naytaJSP("kilpailu", request, response);
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