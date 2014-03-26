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
            
            if (tunnus == null || tunnus.length() == 0) {
                asetaVirhe("Kirjautuminen epäonnistui! Et antanut käyttäjätunnusta.", request);
                naytaJSP("kirjautuminen", request, response);
                return;
            }
            
            request.setAttribute("kayttaja", tunnus);
            
            if (salasana == null || salasana.length() == 0) {
                asetaVirhe("Kirjautuminen epäonnistui! Et antanut salasanaa.", request);
                naytaJSP("kirjautuminen", request, response);
                return;
            }
            
            
            if ("yllapitaja".equals(tunnus) && "qwerty123".equals(salasana)) {
                response.sendRedirect("esittelysivu.jsp");
            }
            
            else {
                asetaVirhe("Kirjautuminen epäonnistui! Antamasi tunnus tai salasana on väärä.", request);
                naytaJSP("kirjautuminen", request, response);
            }
            
        } finally {
            out.close();
        }
    }
    
    
    private void asetaVirhe(String ilmoitus, HttpServletRequest request) {
        request.setAttribute("virheIlmoitus", ilmoitus);
    }
    
    private void naytaJSP(String sivu, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(sivu + ".jsp");
        dispatcher.forward(request, response);
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
