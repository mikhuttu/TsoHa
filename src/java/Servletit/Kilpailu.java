package Servletit;

import Mallit.KilpailuMalli;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
    
public class Kilpailu extends YleisServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        String idParam = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(idParam);
        }
        catch(NumberFormatException e) {
            id = 0;
        }
        
        KilpailuMalli kilpailu = KilpailuMalli.etsi(id);
        request.setAttribute("kilpailu", kilpailu);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        
        try {
            out = response.getWriter();
        }
        catch (IOException e) {}
        
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