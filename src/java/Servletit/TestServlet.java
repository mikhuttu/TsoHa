package Servletit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        
        List<String> asiat = new ArrayList<String>();
        asiat.add("Kirahvi");
        asiat.add("Trumpetti");
        asiat.add("Jeesus");
        asiat.add("Parta");
        
        try {
            out.println("<html>"); 
            out.println("<head><title>Servlet TestiServlet</title></head>");
            out.println("<body>");
            
            out.println("<ul>");
            
            for (String asia: asiat) {
                out.println("<li>" + asia + "</li>");
            }
            
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        } 
    
        finally {            
            out.close();
        }   
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
