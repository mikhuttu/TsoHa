package Servletit;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Kirjautuminen extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            String tunnus = request.getParameter("tunnus");
            String salasana = request.getParameter("salasana");
            
            if ("yllapitaja".equals(tunnus) && "qwerty123".equals(salasana)) {
                response.sendRedirect("esittelysivu");
            }
            
            else {
                request.setAttribute("kayttaja", tunnus);
                RequestDispatcher dispatcher = request.getRequestDispatcher("kirjautuminen.jsp");
                dispatcher.forward(request, response);
            }
            
        } finally {
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
