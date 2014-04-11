package Servletit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OhjaaKirjautumisSivulle extends YleisServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ohjaaKirjautumisSivulleJosEiKirjautunut(request, response);
    }

    @Override
    public String getServletInfo() {
        return "";
    }  
}