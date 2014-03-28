package Servletit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KirjauduUlos extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        kirjauduUlos(request);
        ohjaaSivulle("etusivu", response);
        
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